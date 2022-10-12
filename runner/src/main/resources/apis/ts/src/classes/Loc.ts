export default  class Loc {
    col: number
    row: number

    constructor(col: number, row: number) {
        this.col = col;
        this.row = row;
    }

    public inRange(loc: Loc, range: number) {
        const diff = Math.abs(this.col - loc.col) + Math.abs(this.row - loc.row)
        if (diff === range) return 0
        return diff < range ? 1 : -1
    }
}
