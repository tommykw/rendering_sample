package tokyo.tommy_kw.renderingsample;

import android.graphics.PointF;
import android.graphics.RectF;

/**
 * Created by tomita on 15/10/16.
 */
public class CustomSprite {
    private float angle;
    private float scale;
    private RectF base;
    private PointF translation;

    public CustomSprite() {
        base = new RectF(-50f, 50f, 50f, -50f);
        translation = new PointF(50f, 50f);
        scale = 1f;
        angle = 0f;
    }

    public void translate(float deltax, float deltay) {
        translation.x += deltax;
        translation.y += deltay;
    }

    public void scale(float delta) {
        scale += delta;
    }

    public void rotate(float delta) {
        angle += delta;
    }

    public float[] getTransformedVertices() {
        float x1 = base.left * scale;
        float x2 = base.right * scale;
        float y1 = base.bottom * scale;
        float y2 = base.top * scale;

        PointF one = new PointF(x1, y2);
        PointF two = new PointF(x1, y1);
        PointF three = new PointF(x2, y1);
        PointF four = new PointF(x2, y2);

        float s = (float) Math.sin(angle);
        float c = (float) Math.cos(angle);

        one.x = x1 * c - y2 * s;
        one.y = x1 * s + y2 * c;
        two.x = x1 * c - y1 * s;
        two.y = x1 * s + y1 * c;
        three.x = x2 * c - y1 * s;
        three.y = x2 * s + y1 * c;
        four.x = x2 * c - y2 * s;
        four.y = x2 * s + y2 * c;

        one.x += translation.x;
        one.y += translation.y;
        two.x += translation.x;
        two.y += translation.y;
        three.x += translation.x;
        three.y += translation.y;
        four.x += translation.x;
        four.y += translation.y;

        return new float[] {
                one.x, one.y, 0.0f,
                two.x, two.y, 0.0f,
                three.x, three.y, 0.0f,
                four.x, four.y, 0.0f
        };
    }
}
