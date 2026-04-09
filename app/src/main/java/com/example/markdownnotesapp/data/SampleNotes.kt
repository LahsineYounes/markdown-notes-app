package com.example.markdownnotesapp.data

import com.example.markdownnotesapp.model.Note

/** Example notes shown when the database is empty (first launch). */
object SampleNotes {
    val examples: List<Note> = listOf(
        Note(
            title = "Welcome to Markdown Notes",
            content = """
                # Hello
                This app supports **bold**, *italic*, and `inline code`.

                - Bullet lists
                - Are easy to write

                Tap **+** to add a note, or open this one to edit.
            """.trimIndent(),
            tag = "General"
        ),
        Note(
            title = "Weekly standup",
            content = """
                ## Today
                - Shipped search on the home screen
                - Fixed preview rendering

                ## Blockers
                None — *on track* for Friday.
            """.trimIndent(),
            tag = "Work"
        ),
        Note(
            title = "Book list",
            content = """
                | Priority | Title              |
                |----------|--------------------|
                | Next     | *The Design of Everyday Things* |
                | Later    | **Clean Architecture** |

                > “Good design is as little design as possible.”
            """.trimIndent(),
            tag = "Personal"
        ),
        Note(
            title = "App idea: focus timer",
            content = """
                ### Concept
                Pomodoro + gentle sounds + **no** social feed.

                ### MVP
                1. 25 / 5 minute timers
                2. Daily streak (local only)
                3. Widget on home screen
            """.trimIndent(),
            tag = "Ideas"
        ),
        Note(
            title = "Kotlin snippet",
            content = """
                ```kotlin
                data class Note(
                    val title: String,
                    val content: String
                )

                fun main() {
                    println("Hello, notes!")
                }
                ```
            """.trimIndent(),
            tag = "Work"
        ),
        Note(
            title = "Grocery run",
            content = """
                - [x] Coffee beans
                - [ ] Oats
                - [ ] Greek yogurt
                - [ ] Spinach

                *Store closes at 9pm.*
            """.trimIndent(),
            tag = "Personal"
        ),
        Note(
            title = "Meeting notes — design review",
            content = """
                **Attendees:** Alex, Sam, Jordan

                - Approved **card** layout for the list
                - Prefer *purple* accent over blue for CTAs
                - Follow up: empty state illustration (next sprint)
            """.trimIndent(),
            tag = "Work"
        ),
        Note(
            title = "Quote I liked",
            content = """
                > The best way to predict the future is to invent it.
                > — Alan Kay

                Keeping this in **Ideas** for motivation.
            """.trimIndent(),
            tag = "Ideas"
        ),
        Note(
            title = "Markdown cheat sheet",
            content = """
                # Heading 1
                ## Heading 2

                **bold**  *italic*  `code`

                [Link text](https://example.com)

                ---

                1. Ordered
                2. List
            """.trimIndent(),
            tag = "General"
        ),
        Note(
            title = "Weekend plan",
            content = """
                ### Saturday
                - Morning: hike
                - Afternoon: *nothing* — rest

                ### Sunday
                Meal prep + tidy desk

                ~~Maybe laundry~~ **Definitely** laundry.
            """.trimIndent(),
            tag = "Personal"
        )
    )
}
