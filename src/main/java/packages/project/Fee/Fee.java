package packages.project.Fee;

import lombok.Data;
import packages.project.Area.Area;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table
public class Fee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fee_id")
    int feeId;
    int amount;
    @Column(name="due_date")
    Date dueDate;
    @Column(name = "paid_status")
    boolean paidStatus;

    @ManyToOne
    @JoinColumn(name = "area_id")
    Area area;


    public Fee() {
    }

    public Fee(int feeId, int amount, Date dueDate, boolean paidStatus, Area area) {
        this.feeId = feeId;
        this.amount = amount;
        this.dueDate = dueDate;
        this.paidStatus = paidStatus;
        this.area = area;
    }
}
