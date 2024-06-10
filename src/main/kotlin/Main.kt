data class Likes(val count: Int = 0)

interface Attachment {
    val type: String
}

data class Post(
    val id: Int = 0,
    val createdBy: Int = 0,
    val date: Int = 0,
    val singerId: Int = 0,
    val text: String,
    val attachment: Array<Attachment> = emptyArray(),
    val likes: Likes? = null,
    val postType: String = "post",
    val copyLight: String = "nope",
    val canDelete: Boolean = false,
    val canEdit: Boolean = false,
    val isPinned: Boolean = false

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Post

        if (!attachment.contentEquals(other.attachment)) return false
        if (id != other.id) return false
        if (createdBy != other.createdBy) return false
        if (date != other.date) return false
        if (singerId != other.singerId) return false
        if (text != other.text) return false
        if (likes != other.likes) return false
        if (postType != other.postType) return false
        if (copyLight != other.copyLight) return false
        if (canDelete != other.canDelete) return false
        if (canEdit != other.canEdit) return false
        if (isPinned != other.isPinned) return false

        return true
    }

    override fun hashCode(): Int {
        var result = attachment.contentHashCode()
        result = 31 * result + id
        result = 31 * result + createdBy
        result = 31 * result + date
        result = 31 * result + singerId
        result = 31 * result + text.hashCode()
        result = 31 * result + (likes?.hashCode() ?: 0)
        result = 31 * result + postType.hashCode()
        result = 31 * result + copyLight.hashCode()
        result = 31 * result + canDelete.hashCode()
        result = 31 * result + canEdit.hashCode()
        result = 31 * result + isPinned.hashCode()
        return result
    }
}

data class Photo(
    val id: Int, val ownerId: Int, val photo130: String, val photo604: String
)

data class PhotoAttachment(val photo: Photo) : Attachment {
    override val type: String = "Photo"
}

data class Video(
    val id: Int, val ownerId: Int, val title: String, val duration: Int
)

data class VideoAttachment(val video: Video) : Attachment {
    override val type: String = "Video"
}

data class Graffiti(
    val id: Int, val ownerId: Int, val photo130: String, val photo604: String
)

data class GraffitiAttachment(val graffiti: Graffiti) : Attachment {
    override val type: String = "Graffiti"
}

data class Album(
    val id: Int,
    val thumb: Photo,
    val ownerId: Int,
    val title: String,
    val description: String,
    val created: Int,
    val updated: Int,
    val size: Int
)

data class AlbumAttachment(val album: Album) : Attachment {
    override val type: String = "Album"
}

data class Event(
    val id: Int, val time: Int, val memberStatus: Boolean, val address: String, val text: String
)

data class EventAttachment(val event: Event) : Attachment {
    override val type: String = "Event"
}


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
    //WallService.add(Post(0, 1, 2, 1, "Privet"))
    //WallService.add(Post(0, 2, 4, 5, "Poka"))
    println((PhotoAttachment(Photo(1, 2, "https://vk.com/some_photo_link", "https://vk.com/another_photo_link"))))
    WallService.add(Post(1, 2, 3, 5, "da"))

    WallService.printPost()
}