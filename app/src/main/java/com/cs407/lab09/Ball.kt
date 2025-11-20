package com.cs407.lab09

/**
 * Represents a ball that can move. (No Android UI imports!)
 *
 * Constructor parameters:
 * - backgroundWidth: the width of the background, of type Float
 * - backgroundHeight: the height of the background, of type Float
 * - ballSize: the width/height of the ball, of type Float
 */
class Ball(
    private val backgroundWidth: Float,
    private val backgroundHeight: Float,
    private val ballSize: Float
) {
    var posX = 0f
    var posY = 0f
    var velocityX = 0f
    var velocityY = 0f
    private var accX = 0f
    private var accY = 0f

    private var isFirstUpdate = true

    init {
        reset()
    }

    /**
     * Updates the ball's position and velocity based on the given acceleration and time step.
     * (See lab handout for physics equations)
     */
    fun updatePositionAndVelocity(xAcc: Float, yAcc: Float, dT: Float) {
        if(isFirstUpdate) {
            isFirstUpdate = false
            accX = xAcc
            accY = yAcc
            return
        }

        posX += velocityX * dT + (1f/6f) * (2 * accX + xAcc) * dT * dT
        posY += velocityY * dT + (1f/6f) * (2 * accY + yAcc) * dT * dT
        velocityX += (accX + xAcc) / 2f * dT
        velocityY += (accY + yAcc) / 2f * dT
        accX = xAcc
        accY = yAcc
    }

    /**
     * Ensures the ball does not move outside the boundaries.
     * When it collides, velocity and acceleration perpendicular to the
     * boundary should be set to 0.
     */
    fun checkBoundaries() {
        val radius = ballSize / 2f

        if (posX - radius < 0f) {
            posX = radius
            velocityX = 0f
            accX = 0f
        }

        if (posX + radius > backgroundWidth) {
            posX = backgroundWidth - radius
            velocityX = 0f
            accX = 0f
        }

        if (posY - radius < 0f) {
            posY = radius
            velocityY = 0f
            accY = 0f
        }

        if (posY + radius > backgroundHeight) {
            posY = backgroundHeight - radius
            velocityY = 0f
            accY = 0f
        }
    }

    /**
     * Resets the ball to the center of the screen with zero
     * velocity and acceleration.
     */
    fun reset() {
        posX = backgroundWidth / 2f
        posY = backgroundHeight / 2f
        velocityX = 0f
        velocityY = 0f
        accX = 0f
        accY = 0f
        isFirstUpdate = true
    }
}