package com.zipcodeservice.dto;

public class ZipCode implements Comparable<ZipCode> {

  private int loverBound;
  private int upperBound;

  public ZipCode(int loverBound, int upperBound) {
    this.loverBound = loverBound;
    this.upperBound = upperBound;
  }

  public ZipCode(String loverBound, String upperBound) {
    this.loverBound = Integer.parseInt(loverBound);
    this.upperBound = Integer.parseInt(upperBound);
  }

  public int getLoverBound() {
    return loverBound;
  }

  public void setLoverBound(int loverBound) {
    this.loverBound = loverBound;
  }

  public int getUpperBound() {
    return upperBound;
  }

  public void setUpperBound(int upperBound) {
    this.upperBound = upperBound;
  }

  @Override
  public String toString() {
    return "[" +
        loverBound +
        "," + upperBound +
        ']';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ZipCode)) {
      return false;
    }

    ZipCode zipCode = (ZipCode) o;

    if (loverBound != zipCode.loverBound) {
      return false;
    }
    return upperBound == zipCode.upperBound;
  }

  @Override
  public int hashCode() {
    int result = loverBound;
    result = 31 * result + upperBound;
    return result;
  }

  @Override
  public int compareTo(ZipCode o) {
    return getLoverBound() - o.getLoverBound();
  }
}
