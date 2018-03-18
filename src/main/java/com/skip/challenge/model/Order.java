package com.skip.challenge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonIgnore
    @Column(name = "user_id", nullable = false, updatable = false)
    private Long userId;

    @JsonProperty(value = "status", access = JsonProperty.Access.READ_ONLY)
    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    @JsonProperty(value = "date", access = JsonProperty.Access.READ_ONLY)
    @Column(name = "date", nullable = false, updatable = false)
    private OffsetDateTime date;

    @JsonProperty(value = "lastUpdate", access = JsonProperty.Access.READ_ONLY)
    @Column(name = "last_update", nullable = false)
    private OffsetDateTime lastUpdate;

    @JsonProperty(value = "deliverAddress", required = true)
    @Column(name = "deliver_address", nullable = false)
    @NotEmpty
    private String deliverAddress;

    @JsonProperty(value = "contact", required = true)
    @Column(name = "contact", nullable = false)
    @NotEmpty
    private String contact;

    @JsonProperty(value = "items", required = true)
    @JsonManagedReference
    @Size(min = 1)
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @Valid
    private List<OrderItem> items = new ArrayList<OrderItem>();

    @JsonProperty(value = "total", access = JsonProperty.Access.READ_ONLY)
    @Column(name = "total", nullable = false)
    private BigDecimal total;

    @JsonProperty(value = "cancelationDate", access = JsonProperty.Access.READ_ONLY)
    @Column(name = "cancelation_date")
    private OffsetDateTime cancelationDate;

    @JsonProperty(value = "cancelationReason", access = JsonProperty.Access.READ_ONLY)
    @Column(name = "cancelation_reason", length = 3000)
    private String cancelationReason;

    public Order() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public OffsetDateTime getDate() {
        return date;
    }

    public void setDate(OffsetDateTime date) {
        this.date = date;
    }

    public OffsetDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(OffsetDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getDeliverAddress() {
        return deliverAddress;
    }

    public void setDeliverAddress(String deliverAddress) {
        this.deliverAddress = deliverAddress;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public OffsetDateTime getCancelationDate() {
        return cancelationDate;
    }

    public void setCancelationDate(OffsetDateTime cancelationDate) {
        this.cancelationDate = cancelationDate;
    }

    public String getCancelationReason() {
        return cancelationReason;
    }

    public void setCancelationReason(String cancelationReason) {
        this.cancelationReason = cancelationReason;
    }
}
