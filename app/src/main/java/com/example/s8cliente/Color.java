package com.example.s8cliente;

public class Color {

        private String type = "colorcito";

        private int r,g,b;


        public Color() {
        }
        public Color(int r,int g,int b) {
            super();
            this.r = r;
            this.g = g;

            this.b = b;
        }

        public int getR() {
            return r;
        }

        public void setR(int r) {
            this.r = r;
        }

        public int getG() {
            return g;
        }

        public void setG(int g) {
            this.g = g;
        }

        public int getB() {
            return b;
        }

        public void setB(int b) {
            this.b = b;
        }

    }



