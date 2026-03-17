package model;
import java.util.List;

public class Order {
    private int id;
    private String customerName;
    private String phone;
    private String address;
    private double totalAmount;
    private String status;
    private List<OrderDetail> details; // Danh sách các món hàng nằm trong đơn này

    public Order(int id, String customerName, String phone, String address, double totalAmount, String status) {
        this.id = id;
        this.customerName = customerName;
        this.phone = phone;
        this.address = address;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public int getId() { return id; }
    public String getCustomerName() { return customerName; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public double getTotalAmount() { return totalAmount; }
    public String getStatus() { return status; }
    
    public List<OrderDetail> getDetails() { return details; }
    public void setDetails(List<OrderDetail> details) { this.details = details; }
}
