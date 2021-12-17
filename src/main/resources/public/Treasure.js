export default class Treasure {
    constructor(x, y, tileSize) {
        this.x = x;
        this.y = y;
        this.tileSize = tileSize;

        this.#loadTreasureImages();
    }

    draw(ctx, number) {
        const size = this.tileSize / 2;

        ctx.save();
        ctx.translate(this.x + size, this.y + size);
        ctx.rotate((this.currentMovingDirection * 90 * Math.PI) / 180);
        ctx.drawImage(
            this.pacmanImages[number],
            -size,
            -size,
            this.tileSize,
            this.tileSize
        );

        ctx.restore();
    }

    #loadTreasureImages() {
        const pacmanImage1 = new Image();
        pacmanImage1.src = "images/fruits/Fruit1.png";

        const pacmanImage2 = new Image();
        pacmanImage2.src = "images/fruits/Fruit2.png";

        const pacmanImage3 = new Image();
        pacmanImage3.src = "images/fruits/Fruit3.png";

        this.pacmanImages = [
            pacmanImage1,
            pacmanImage2,
            pacmanImage3,
        ];
    }

    updatePosition(x, y, tileSize) {
        this.x = x;
        this.y = y;
        this.tileSize = tileSize;
    }
}