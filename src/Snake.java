public class Snake extends Obstacle {

    public Snake() {
        super(4, "Yilan", (int) ((Math.random() * (6 - 3)) + 3), 12, 0);
    }

}
