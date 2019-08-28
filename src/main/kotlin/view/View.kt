package view

import DISPLAY_HEIGHT
import DISPLAY_WIDTH
import org.lwjgl.Version
import org.lwjgl.glfw.Callbacks
import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFW.glfwInit
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11
import org.lwjgl.system.MemoryStack
import org.lwjgl.system.MemoryUtil
import Game
class View {
    private var window: Long = 0
    fun run() {
        System.out.println("Hello LWJGL " + Version.getVersion() + "!")

        init()
        loop()

        Callbacks.glfwFreeCallbacks(window)
        GLFW.glfwDestroyWindow(window)

        GLFW.glfwTerminate()
        GLFW.glfwSetErrorCallback(null)?.free()
    }

    private fun init() {
        GLFWErrorCallback.createPrint(System.err).set()

        if (!glfwInit())
            throw IllegalStateException("Unable to initialize GLFW")

        GLFW.glfwDefaultWindowHints()
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE)
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE)

        window = GLFW.glfwCreateWindow(DISPLAY_WIDTH, DISPLAY_HEIGHT, "Hello World!", MemoryUtil.NULL, MemoryUtil.NULL)
        if (window == MemoryUtil.NULL)
            throw RuntimeException("Failed to create the GLFW window")

        GLFW.glfwSetKeyCallback(window) { window, key, scancode, action, mods ->
            if (key === GLFW.GLFW_KEY_ESCAPE && action === GLFW.GLFW_RELEASE)
                GLFW.glfwSetWindowShouldClose(window, true)
        }

        MemoryStack.stackPush().use { stack ->
            val pWidth = stack.mallocInt(1)
            val pHeight = stack.mallocInt(1)

            GLFW.glfwGetWindowSize(window, pWidth, pHeight)

            val vidmode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor())

            GLFW.glfwSetWindowPos(
                window,
                (vidmode!!.width() - pWidth.get(0)) / 2,
                (vidmode.height() - pHeight.get(0)) / 2
            )
        }

        GLFW.glfwMakeContextCurrent(window)
        GLFW.glfwSwapInterval(1)

        GLFW.glfwShowWindow(window)
    }

    private fun loop() {
        GL.createCapabilities()
        GLFW.glfwWindowHint(GLFW.GLFW_STENCIL_BITS, 4)
        GLFW.glfwWindowHint(GLFW.GLFW_SAMPLES, 4)
        var a1 = 0.0
        var b1 = 0.0
        var c1 = 0.0
        var game = Game()
        game.create();
        while (!GLFW.glfwWindowShouldClose(window)) {
            a1+=0.01*Math.random()
            b1+=0.03*Math.random()
            c1+=0.05*Math.random()

            GL11.glClearColor(
                Math.sin(a1).toFloat(),
                Math.sin(b1).toFloat(),
                Math.sin(c1).toFloat(),
                1f
            )
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT or GL11.GL_DEPTH_BUFFER_BIT)
            GLFW.glfwPollEvents()
            game.update()
            if(game.isEnd){
                game.create()
            }
            GLFW.glfwSwapBuffers(window)

        }
    }
}