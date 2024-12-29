package com.shopnow.customer.build;

import lombok.Getter;

@Getter
public class Computer {

    //required parameters
    private final String HDD;
    private final String RAM;

    //optional parameters
    private final boolean isGraphicsCardEnabled;
    private final boolean isBluetoothEnabled;

    private Computer(ComputerBuilder builder) {
        this.HDD=builder.HDD;
        this.RAM=builder.RAM;
        this.isGraphicsCardEnabled=builder.isGraphicsCardEnabled;
        this.isBluetoothEnabled=builder.isBluetoothEnabled;
    }

    //Builder Class
    public static class ComputerBuilder{

        // required parameters
        private String HDD;
        private String RAM;

        // optional parameters
        private boolean isGraphicsCardEnabled;
        private boolean isBluetoothEnabled;

        public ComputerBuilder setHDD(String hdd) {
            this.HDD=hdd;
            return this;
        }

        public ComputerBuilder setRAM(String ram) {
            this.RAM=ram;
            return this;
        }

        public ComputerBuilder setGraphicsCardEnabled(boolean isGraphicsCardEnabled) {
            this.isGraphicsCardEnabled = isGraphicsCardEnabled;
            return this;
        }

        public ComputerBuilder setBluetoothEnabled(boolean isBluetoothEnabled) {
            this.isBluetoothEnabled = isBluetoothEnabled;
            return this;
        }

        public Computer build(){
            return new Computer(this);
        }

    }
}