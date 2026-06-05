package me.foksik.utility.internal.chat

import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver
import net.kyori.adventure.text.minimessage.tag.standard.StandardTags

internal val miniMessage = MiniMessage.builder()
    .tags(TagResolver.builder()
        .resolver(StandardTags.color())
        .resolver(StandardTags.gradient())
        .resolver(StandardTags.rainbow())
        .resolver(StandardTags.decorations())
        .build()
    ).build()