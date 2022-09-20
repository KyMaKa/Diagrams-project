package logic.types;

public enum Annotations {
  NOT_NULL {
    public String toString() {
      return "NOT NULL";
    }
  },
  PK {
    public String toString() {
      return "PRIMARY KEY";
    }
  },
  FK {
    public String toString() {
      return "FOREIGN KEY";
    }
  },
  UNIQUE,
}
