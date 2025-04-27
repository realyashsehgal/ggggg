package src.models;

import java.time.LocalDate;

public class Transaction {

    private Integer transactionId;
    private String studentErp;
    private String bookId;

    private LocalDate transactionDate;
    private LocalDate dueDate;

    private String type;

    public Transaction(Integer transactionId, String studentErp, String bookId, String type, LocalDate transactionDate,
            LocalDate dueDate) {
        this.transactionId = transactionId;
        this.studentErp = studentErp;
        this.bookId = bookId;
        this.type = type;
        this.transactionDate = transactionDate;
        this.dueDate = dueDate;

    }

    public Transaction(String studentErp, String bookId, String type) {
        this.studentErp = studentErp;
        this.bookId = bookId;
        this.type = type;

    }

    public int getId() {
        return transactionId;
    }

    public String getStudentErp() {
        return studentErp;
    }

    public String getBookId() {
        return bookId;
    }

    public String getType() {
        return type;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        return "Transaction ID: " + transactionId + ", Student ERP: " + studentErp + ", Book ID: " + bookId + ", Type: "
                + type + "\n";
    }

}
