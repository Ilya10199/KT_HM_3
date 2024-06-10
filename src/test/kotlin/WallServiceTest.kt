import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class WallServiceTest {

    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }


    @Test
    fun add() {
        val id: Int = 0
        val createdBy: Int = 0
        val date: Int = 0
        val singerId: Int = 0
        val text: String = "Hi"

        val result =
            WallService.add(Post(id = id, createdBy = createdBy, date = date, singerId = singerId, text = text))
        assertEquals(1, result.id)
    }

    @Test
    fun updateTrue() {
        val id: Int = 1
        val createdBy: Int = 0
        val date: Int = 0
        val singerId: Int = 0
        val text: String = "Hi"
        WallService.add(Post(id = id, createdBy = createdBy, date = date, singerId = singerId, text = text))
        val result =
            WallService.update(Post(id = 1, createdBy = createdBy, date = date, singerId = singerId, text = text))
        assertTrue(result)
    }

    @Test
    fun updateFalse() {
        val id: Int = 1
        val createdBy: Int = 0
        val date: Int = 0
        val singerId: Int = 0
        val text: String = "Hi"
        WallService.add(Post(id = id, createdBy = createdBy, date = date, singerId = singerId, text = text))
        val result =
            WallService.update(Post(id = 2, createdBy = createdBy, date = date, singerId = singerId, text = text))
        assertFalse(result)
    }

    @Test(expected = PostNotFoundException::class)
    fun shouldThrow() {
        val id: Int = 1
        val createdBy: Int = 0
        val date: Int = 0
        val singerId: Int = 0
        val text: String = "Hi"
        WallService.add(Post(id = id, createdBy = createdBy, date = date, singerId = singerId, text = text))
        WallService.createComment(2, Comment(1, 2, 3, "izi"))
    }

    @Test
    fun testComment(){
        val id: Int = 1
        val id1: Int = 2
        val createdBy: Int = 0
        val date: Int = 0
        val singerId: Int = 0
        val text: String = "Hi"
        val resultPost =  WallService.add(Post(id = 1, createdBy = createdBy, date = date, singerId = singerId, text = text))
        val resultComment = WallService.createComment(1, Comment(2, 2, 3, "izi"))
        assertEquals(resultPost.id, resultComment.id)
    }

}