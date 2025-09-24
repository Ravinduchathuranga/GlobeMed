package com.rc008code.hms.reports.api;


public interface ReportElement {
    void accept(ReportVisitor visitor);
}
