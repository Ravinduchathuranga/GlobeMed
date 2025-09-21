package com.rc008code.hms.reports.api;

/**
 * Marker interface for reportable elements that can be visited by a ReportVisitor.
 */
public interface ReportElement {
    void accept(ReportVisitor visitor);
}
