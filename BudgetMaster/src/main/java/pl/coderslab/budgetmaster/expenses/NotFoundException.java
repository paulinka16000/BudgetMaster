package pl.coderslab.budgetmaster.expenses;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
