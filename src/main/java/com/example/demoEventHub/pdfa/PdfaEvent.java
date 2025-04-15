package com.example.demoEventHub.pdfa;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PdfaEvent {
    private String customerId;
    private String conversionJobId;
    private String status;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getConversionJobId() {
        return conversionJobId;
    }

    public void setConversionJobId(String conversionJobId) {
        this.conversionJobId = conversionJobId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
