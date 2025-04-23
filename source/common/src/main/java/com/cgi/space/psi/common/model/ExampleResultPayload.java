package com.cgi.space.psi.common.model;

/**
 * Example implementation of {@link ResultPayload}.
 */
public class ExampleResultPayload extends ResultPayload {

    private int packetsIn;
    private int charsIn;
    private int packetsOut;
    private int charsOut;

    public ExampleResultPayload() {
        setAtType("urn:mef:lso:spec:legato:ip-performance-monitoring-results:v0.0.1:all");
    }

    public int getPacketsIn() {
        return packetsIn;
    }

    public void setPacketsIn(int packetsIn) {
        this.packetsIn = packetsIn;
    }

    public ExampleResultPayload packetsIn(int packetsIn) {
        this.packetsIn = packetsIn;
        return this;
    }

    public int getCharsIn() {
        return charsIn;
    }

    public void setCharsIn(int charsIn) {
        this.charsIn = charsIn;
    }

    public ExampleResultPayload charsIn(int charsIn) {
        this.charsIn = charsIn;
        return this;
    }

    public int getPacketsOut() {
        return packetsOut;
    }

    public void setPacketsOut(int packetsOut) {
        this.packetsOut = packetsOut;
    }

    public ExampleResultPayload packetsOut(int packetsOut) {
        this.packetsOut = packetsOut;
        return this;
    }

    public int getCharsOut() {
        return charsOut;
    }

    public void setCharsOut(int charsOut) {
        this.charsOut = charsOut;
    }

    public ExampleResultPayload charsOut(int charsOut) {
        this.charsOut = charsOut;
        return this;
    }

}
