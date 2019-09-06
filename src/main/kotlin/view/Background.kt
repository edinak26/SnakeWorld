package view

import org.lwjgl.glfw.GLFW
import org.lwjgl.opengl.GL11
import view.entities.Colored

class Background : Colored() {

    fun update() = updateColor()


    fun draw() {
        GL11.glClearColor(activatedR, activatedG, activatedB, 1f)
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT or GL11.GL_DEPTH_BUFFER_BIT)
        GLFW.glfwPollEvents()
    }
}