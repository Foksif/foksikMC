package me.foksik.bootstrap

/**
 * Defines module loading priority.
 *
 * Note:
 * Dependency resolution has higher priority
 * than this enum ordering.
 */
enum class LoadPriority {
    LOW,
    NORMAL,
    HIGH
}