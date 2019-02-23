package ywxt.tools.common.http


fun String.middleIn(start: String, end: String): String {
    val s = this.indexOf(start)
    val e = this.indexOf(end, s + 1)
    return this.substring((s + start.length)..(e - 1))
}