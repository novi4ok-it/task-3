package com.cgvsu.objwriter;

import com.cgvsu.math.Vector2f;
import com.cgvsu.math.Vector3f;
import com.cgvsu.model.Model;
import com.cgvsu.model.Polygon;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ObjWriter {

    public static void write(Model model, String filename) throws IOException {
        File file = new File(filename);
        FileWriter writer = new FileWriter(file);

        // Запись вершин
        for (Vector3f vertex : model.vertices) {
            writer.write("v " + vertex.x + " " + vertex.y + " " + vertex.z + "\n");
        }

        // Запись вершин текстур
        for (Vector2f textureVertex : model.textureVertices) {
            writer.write("vt " + textureVertex.x + " " + textureVertex.y + "\n");
        }

        // Запись нормалей
        for (Vector3f normal : model.normals) {
            writer.write("vn " + normal.x + " " + normal.y + " " + normal.z + "\n");
        }

        // Запись полигонов
        for (Polygon polygon : model.polygons) {
            writer.write("f ");
            List<Integer> vertexIndices = polygon.getVertexIndices();
            List<Integer> textureVertexIndices = polygon.getTextureVertexIndices();
            List<Integer> normalIndices = polygon.getNormalIndices();

            for (int i = 0; i < vertexIndices.size(); i++) {
                writer.write((vertexIndices.get(i) + 1) + " "); // Индексы в OBJ начинаются с 1
                if (!textureVertexIndices.isEmpty()) {
                    writer.write((textureVertexIndices.get(i) + 1) + " "); // Индексы в OBJ начинаются с 1
                }
                if (!normalIndices.isEmpty()) {
                    writer.write((normalIndices.get(i) + 1) + " "); // Индексы в OBJ начинаются с 1
                }
            }
            writer.write("\n");
        }

        writer.close();
    }
}