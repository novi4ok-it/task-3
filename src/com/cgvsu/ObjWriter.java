package com.cgvsu;

import com.cgvsu.model.Model;

import java.io.IOException;

public interface ObjWriter {
    void write(Model model, String filename) throws IOException;
}
