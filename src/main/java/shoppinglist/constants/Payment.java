package shoppinglist.constants;

public enum Payment {
    CASH ("cash"),
    DEBIT_CARD ("debit card"),
    CREDIT_CARD ("credit card");
    private final String name;

    Payment(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
