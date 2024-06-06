data class Likes(val count: Int = 0)

data class Post(
    val id: Int = 0,
    val createdBy: Int = 0,
    val date: Int = 0,
    val singerId: Int = 0,
    val text: String,
    val likes: Likes = Likes(),
    val postType: String = "post",
    val copyLight: String = "nope",
    val canDelete: Boolean = false,
    val canEdit: Boolean = false,
    val isPinned: Boolean = false

)

object WallService {
    private var posts = emptyArray<Post>()
    private var lastId = 0

    fun clear() {
        posts = emptyArray()
        lastId = 0

    }

    fun add(post: Post): Post {
        posts += post.copy(id = ++lastId)
        return posts.last()
    }

    fun update(upPost: Post): Boolean {
        for ((index, post) in posts.withIndex()) {
            if (post.id == upPost.id) {
                posts[index] = upPost.copy()
                return true
            }
        }
        return false
    }

    fun printPost() {
        for (post in posts) {
            print(post)
            println(" ")
        }
        println()
    }
}

fun main() {

    WallService.add(Post(0, 1, 2, 1, "Privet"))
    WallService.add(Post(0, 2, 4, 5, "Poka"))
    WallService.add(Post(0, 1, 2, 13, "Privet"))
    WallService.printPost()
}