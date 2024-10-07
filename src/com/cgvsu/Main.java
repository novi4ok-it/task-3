package com.cgvsu;

import com.cgvsu.math.Vector2f;
import com.cgvsu.math.Vector3f;
import com.cgvsu.model.Model;
import com.cgvsu.model.Polygon;
import com.cgvsu.objreader.ObjReader;
import com.cgvsu.objwriter.ObjWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        Model model1 = new Model();
        model1.vertices = new ArrayList<>(Arrays.asList(
                new Vector3f(1.0f, 2.0f, 3.0f),
                new Vector3f(4.0f, 5.0f, 6.0f),
                new Vector3f(7.0f, 8.0f, 9.0f)
        ));
        model1.textureVertices = new ArrayList<>(Arrays.asList(
                new Vector2f(0.1f, 0.2f),
                new Vector2f(0.3f, 0.4f),
                new Vector2f(0.5f, 0.6f)
        ));
        model1.normals = new ArrayList<>(Arrays.asList(
                new Vector3f(-1.0f, -2.0f, -3.0f),
                new Vector3f(-4.0f, -5.0f, -6.0f),
                new Vector3f(-7.0f, -8.0f, -9.0f)
        ));
        model1.polygons = new ArrayList<>(Arrays.asList(
                new Polygon() {{
                    setVertexIndices(new ArrayList<>(Arrays.asList(0, 1, 2)));
                    setTextureVertexIndices(new ArrayList<>(Arrays.asList(0, 1, 2)));
                    setNormalIndices(new ArrayList<>(Arrays.asList(0, 1, 2)));
                }}
        ));

        String filename = "test1.obj";
        ObjWriter.write(model1, filename);
        File file = new File(filename);

        Path fileName = Path.of("X:\\tasks\\2 kurs\\Task3\\ObjReaderInitial\\test1.obj");
        String fileContent = Files.readString(fileName);

        System.out.println("Loading model ...");
        Model model = ObjReader.read(fileContent);

        System.out.println("Vertices: " + model.vertices.size());
        System.out.println("Texture vertices: " + model.textureVertices.size());
        System.out.println("Normals: " + model.normals.size());
        System.out.println("Polygons: " + model.polygons.size());
    }
}
