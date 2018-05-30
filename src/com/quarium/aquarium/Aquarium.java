package com.quarium.aquarium;

import com.quarium.util.CustomList;
import com.quarium.util.ListException;


public class Aquarium {
  private int hsize;
  private int vsize;
  private CustomList<Guppy> listGuppy;
  private CustomList<Piranha> listPiranha;
  private CustomList<Coin> listCoin;
  private CustomList<Food> listFood;
  private Snail siput;

  /**
   * Aquarium where the game takes place.
   * @param horizontalSize Width of game window
   * @param verticalSize Height of game window
   */
  public Aquarium(int horizontalSize, int verticalSize) {
    hsize = horizontalSize;
    vsize = verticalSize;
    siput = new Snail(hsize / 2, vsize - 50);
    listCoin = new CustomList<Coin>();
    listGuppy = new CustomList<Guppy>();
    listFood = new CustomList<Food>();
    listPiranha = new CustomList<Piranha>();
    Guppy newGuppy = new Guppy(100,100,0);
    try {
      addToListGuppy(newGuppy);
    } catch (ListException e) {
      e.displayMsg();
    }
  }

  public void addToListGuppy(Guppy newGuppy) throws ListException {
    listGuppy.add(newGuppy);
  }

  public void addToListPiranha(Piranha newPiranha) throws ListException {
    listPiranha.add(newPiranha);
  }

  public void addToListCoin(Coin newCoin) throws ListException {
    listCoin.add(newCoin);
  }

  public void addToListFood(Food newFood) throws ListException {
    listFood.add(newFood);
  }

  public void removeFromListGuppy(Guppy currGuppy) throws ListException {
    listGuppy.remove(currGuppy);
  }

  public void removeFromListPiranha(Piranha currPiranha) throws ListException {
    listPiranha.remove(currPiranha);
  }

  public void removeFromListCoin(Coin currCoin) throws ListException {
    listCoin.remove(currCoin);
  }

  public void removeFromListFood(Food currFood) throws ListException {
    listFood.remove(currFood);
  }

  public int getHsize() {
    return hsize;
  }

  public void setHsize(int hsize) {
    this.hsize = hsize;
  }

  public int getVsize() {
    return vsize;
  }

  public void setVsize(int vsize) {
    this.vsize = vsize;
  }

  public CustomList<Guppy> getListGuppy() {
    return listGuppy;
  }

  public void setListGuppy(CustomList<Guppy> listGuppy) {
    this.listGuppy = listGuppy;
  }

  public CustomList<Piranha> getListPiranha() {
    return listPiranha;
  }

  public void setListPiranha(CustomList<Piranha> listPiranha) {
    this.listPiranha = listPiranha;
  }

  public CustomList<Coin> getListCoin() {
    return listCoin;
  }

  public void setListCoin(CustomList<Coin> listCoin) {
    this.listCoin = listCoin;
  }

  public CustomList<Food> getListFood() {
    return listFood;
  }

  public void setListFood(CustomList<Food> listFood) {
    this.listFood = listFood;
  }

  public Snail getSiput() {
    return siput;
  }

  public void setSiput(Snail siput) {
    this.siput = siput;
  }

  /**
   * Update all the coins location.
   * @throws ListException If something wrong happens with the list.
   */
  public void updateAllCoins() throws ListException {
    for (int iterator = 0; iterator < listCoin.size(); iterator++) {
      Coin c = listCoin.get(iterator);
      if (c.getYpos() < vsize - 50) {
        c.setYpos(c.getYpos() + c.getYspeed());
      } else {
        c.setYpos(vsize - 50);
      }
    }
  }

  /**
   * Update all Food in Aquarium.
   * @throws ListException in case the listFood is empty.
   */
  public void updateAllFood() throws ListException {
    for (int iterator = 0; iterator < listFood.size(); iterator++) {
      Food f = listFood.get(iterator);
      if (f.getYpos() > vsize - 50) {
        removeFromListFood(f);
      } else {
        f.setYpos(f.getYpos() + f.getYspeed());
      }
    }
  }

  /**
   * Update Snail position.
   * @throws ListException in case the listCoin is empty
   */
  public void updateSnail() throws ListException {
    if (listCoin.isEmpty()) {
      siput.stopMovement();
    } else {
      Coin c = getNearestCoin(siput);
      if (siput.inRangeX(c.getXpos(), 5) && siput.inRangeY(c.getYpos(), 5)) {
        siput.acceptCoin(c);
        removeFromListCoin(c);
        siput.stopMovement();
      } else {
        if (siput.inRangeX(c.getXpos(), 5)) {
          siput.setXspeed(0);
        } else if (c.getXpos() > siput.getXpos()) {
          siput.setXspeed(1);
          siput.setSpriteFacing(true);
        } else if (c.getXpos() < siput.getXpos()) {
          siput.setXspeed(-1);
          siput.setSpriteFacing(false);
        }
        if (siput.getXpos() < hsize && siput.getXpos() > 0) {
          siput.setXpos(siput.getXpos() + Snail.SNAIL_SPEED * siput.getXspeed());
        } else {
          siput.setXspeed(siput.getXspeed() * -1);
          siput.setSpriteFacing(!siput.getSpriteFacing());
          siput.setXpos(siput.getXpos() + Snail.SNAIL_SPEED * siput.getXspeed());
        }
      }
    }
  }

  /**
   * Update all Guppy position.
   * @param currTime current time at the moment
   * @throws ListException if list empty
   */
  public void updateAllGuppy(double currTime) throws ListException {
    int idxGuppy = 0;
    while (idxGuppy < listGuppy.size()) {
      Guppy guppy = listGuppy.get(idxGuppy);
      if (currTime - guppy.getLastGenerateCoin() > guppy.GUPPY_COIN) {
        Coin newCoin = new Coin(guppy.getXpos(), guppy.getYpos(), guppy.getCoin());
        addToListCoin(newCoin);
        guppy.setLastGenerateCoin(currTime);
      }
      if (guppy.isFull()) {
        if (currTime - guppy.getTimehungry() > guppy.GUPPY_HUNGRY) {
          guppy.setHungry();
          idxGuppy++;
        } else {
          if (currTime - guppy.getTimeprevmove() > guppy.GUPPY_CHANGE_MOVE) {
            guppy.changeMovement();
            guppy.setTimeprevmove(currTime);
          }
          guppy.updatePosition(hsize,vsize);
          idxGuppy++;
        }
      } else {
        if (currTime - guppy.getTimehungry() > guppy.GUPPY_DEAD) {
          removeFromListGuppy(guppy);
        } else {
          if (listFood.isEmpty()) {
            if (currTime - guppy.getTimeprevmove() > guppy.GUPPY_CHANGE_MOVE) {
              guppy.changeMovement();
              guppy.setTimeprevmove(currTime);
            }
            guppy.updatePosition(hsize,vsize);
            idxGuppy++;
          } else {
            Food f = getNearestFood(guppy);
            if (guppy.inRangeX(f.getXpos(), 5) && guppy.inRangeY(f.getYpos(), 5)) {
              guppy.eat(f);
              removeFromListFood(f);
              guppy.grow();
              guppy.setTimehungry(currTime);
              idxGuppy++;
            } else {
              guppy.chaseFood(f);
              guppy.updatePosition(hsize, vsize);
              idxGuppy++;
            }
          }
        }
      }
    }
  }

  /**
   * Update all Piranha position.
   * @param currTime current time at the moment
   * @throws ListException jika list kosong atau elemen yang dicari tidak ada
   */
  public void updateAllPiranha(double currTime) throws ListException {
    int idxPir = 0;
    while (idxPir < listPiranha.size()) {
      Piranha piranha = listPiranha.get(idxPir);
      if (piranha.isFull()) {
        if (currTime - piranha.getTimehungry() > piranha.PIRANHA_TIME_INTERVAL_HUNGRY) {
          piranha.setHungry();
          idxPir++;
        } else {
          if (currTime - piranha.getTimeprevmove() > 4) {
            piranha.changeMovement();
            piranha.setTimeprevmove(currTime);
          }
          piranha.updatePosition(hsize, vsize);
          idxPir++;
        }
      } else {
        if (currTime - piranha.getTimehungry() > piranha.PIRANHA_TIME_INTERVAL_DEATH) {
          removeFromListPiranha(piranha);
        } else {
          if (listGuppy.isEmpty()) {
            if (currTime - piranha.getTimeprevmove() > 4) {
              piranha.changeMovement();
              piranha.setTimeprevmove(currTime);
            }
            piranha.updatePosition(hsize, vsize);
            idxPir++;
          } else {
            Guppy g = getNearestGuppy(piranha);
            if (piranha.inRangeX(g.getXpos(), 5) && piranha.inRangeY(g.getYpos(), 5)) {
              int guppyLvl = g.getGrowthStage();
              Coin newCoin = new Coin(piranha.getXpos(), piranha.getYpos(), 100 * (guppyLvl + 1));
              addToListCoin(newCoin);
              piranha.eat(g);
              removeFromListGuppy(g);
              piranha.setTimehungry(currTime);
              idxPir++;
            } else {
              piranha.chaseFood(g);
              piranha.updatePosition(hsize, vsize);
              idxPir++;
            }
          }
        }
      }
    }
  }


  /**
   * Mengembalikan Coin terdekat dari posisi Item x.
   * @param x Item yang akan dicarikan Coin terdekat
   * @return Coin terdekat dari x
   * @throws ListException jika list kosong atau elemen yang dicari tidak ada
   */
  public Coin getNearestCoin(Item x) throws ListException {
    double minDistance = 10000000;
    int idx = -1;
    for (int iterator = 0; iterator < listCoin.size(); iterator++) {
      Coin c = listCoin.get(iterator);
      double dist = x.getDistance(c);
      if (dist < minDistance) {
        minDistance = dist;
        idx = iterator;
      }
    }
    return listCoin.get(idx);
  }

  /**
   * Mengembalikan Guppy terdekat dari posisi Item x.
   * @param x Item yang akan dicarikan Guppy terdekat
   * @return Guppy terdekat dari x
   * @throws ListException jika list kosong atau elemen yang dicari tidak ada
   */
  public Guppy getNearestGuppy(Item x) throws ListException {
    double minDistance = 10000000;
    int idx = -1;
    for (int iterator = 0; iterator < listGuppy.size(); iterator++) {
      Guppy g = listGuppy.get(iterator);
      double dist = x.getDistance(g);
      if (dist < minDistance) {
        minDistance = dist;
        idx = iterator;
      }
    }
    return listGuppy.get(idx);
  }

  /**
   * Mengembalikan Food terdekat dari posisi Item x.
   * @param x Item yang akan dicarikan Food terdekat
   * @return Food terdekat dari x
   * @throws ListException jika list kosong atau elemen yang dicari tidak ada
   */
  public Food getNearestFood(Item x) throws ListException {
    double minDistance = 10000000;
    int idx = -1;
    for (int iterator = 0; iterator < listFood.size(); iterator++) {
      Food f = listFood.get(iterator);
      double dist = x.getDistance(f);
      if (dist < minDistance) {
        minDistance = dist;
        idx = iterator;
      }
    }
    return listFood.get(idx);
  }
}
