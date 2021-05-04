package com.example.laboratorio4.dto;

import java.math.BigDecimal;
import java.util.Date;

public interface Empleadosporsalario {
    String getFirst_name();
    String getLast_name();
    Date getStart_date();
    Date getEnd_date();
    String getJob_title();
    BigDecimal getSalary();
}
