package objwriter;

import com.cgvsu.math.Vector2f;
import com.cgvsu.math.Vector3f;
import com.cgvsu.model.Model;
import com.cgvsu.model.Polygon;
import com.cgvsu.objwriter.ObjWriter;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ObjWriterTest {

    @Test
    void testWrite() throws IOException {
        Model model = new Model();
        model.vertices = new ArrayList<>(Arrays.asList(
                new Vector3f(1.0f, 2.0f, 3.0f),
                new Vector3f(4.0f, 5.0f, 6.0f),
                new Vector3f(7.0f, 8.0f, 9.0f)
        ));
        model.textureVertices = new ArrayList<>(Arrays.asList(
                new Vector2f(0.1f, 0.2f),
                new Vector2f(0.3f, 0.4f),
                new Vector2f(0.5f, 0.6f)
        ));
        model.normals = new ArrayList<>(Arrays.asList(
                new Vector3f(-1.0f, -2.0f, -3.0f),
                new Vector3f(-4.0f, -5.0f, -6.0f),
                new Vector3f(-7.0f, -8.0f, -9.0f)
        ));
        model.polygons = new ArrayList<>(Arrays.asList(
                new Polygon() {{
                    setVertexIndices(new ArrayList<>(Arrays.asList(0, 1, 2)));
                    setTextureVertexIndices(new ArrayList<>(Arrays.asList(0, 1, 2)));
                    setNormalIndices(new ArrayList<>(Arrays.asList(0, 1, 2)));
                }}
        ));

        String filename = "test.obj";
        com.cgvsu.ObjWriter objWriter = new ObjWriter();
        objWriter.write(model, filename);

        List<String> fileLines = Files.readAllLines(Paths.get(filename));

        List<String> expectedLines = Arrays.asList(
                "v 1.0 2.0 3.0",
                "v 4.0 5.0 6.0",
                "v 7.0 8.0 9.0",
                "vt 0.1 0.2",
                "vt 0.3 0.4",
                "vt 0.5 0.6",
                "vn -1.0 -2.0 -3.0",
                "vn -4.0 -5.0 -6.0",
                "vn -7.0 -8.0 -9.0",
                "f 1/1/1 2/2/2 3/3/3 "
        );

        assertEquals(expectedLines, fileLines);

        File file = new File(filename);
        file.delete();
    }
}