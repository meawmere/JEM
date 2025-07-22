package net.meawmere.jem.infrastructure.services;

import net.dv8tion.jda.api.events.*;
import net.dv8tion.jda.api.events.automod.*;
import net.dv8tion.jda.api.events.channel.ChannelCreateEvent;
import net.dv8tion.jda.api.events.channel.ChannelDeleteEvent;
import net.dv8tion.jda.api.events.channel.GenericChannelEvent;
import net.dv8tion.jda.api.events.channel.forum.ForumTagAddEvent;
import net.dv8tion.jda.api.events.channel.forum.ForumTagRemoveEvent;
import net.dv8tion.jda.api.events.channel.forum.GenericForumTagEvent;
import net.dv8tion.jda.api.events.channel.forum.update.ForumTagUpdateEmojiEvent;
import net.dv8tion.jda.api.events.channel.forum.update.ForumTagUpdateModeratedEvent;
import net.dv8tion.jda.api.events.channel.forum.update.ForumTagUpdateNameEvent;
import net.dv8tion.jda.api.events.channel.forum.update.GenericForumTagUpdateEvent;
import net.dv8tion.jda.api.events.channel.update.*;
import net.dv8tion.jda.api.events.emoji.EmojiAddedEvent;
import net.dv8tion.jda.api.events.emoji.EmojiRemovedEvent;
import net.dv8tion.jda.api.events.emoji.GenericEmojiEvent;
import net.dv8tion.jda.api.events.emoji.update.EmojiUpdateNameEvent;
import net.dv8tion.jda.api.events.emoji.update.EmojiUpdateRolesEvent;
import net.dv8tion.jda.api.events.emoji.update.GenericEmojiUpdateEvent;
import net.dv8tion.jda.api.events.entitlement.EntitlementCreateEvent;
import net.dv8tion.jda.api.events.entitlement.EntitlementDeleteEvent;
import net.dv8tion.jda.api.events.entitlement.EntitlementUpdateEvent;
import net.dv8tion.jda.api.events.entitlement.GenericEntitlementEvent;
import net.dv8tion.jda.api.events.guild.*;
import net.dv8tion.jda.api.events.guild.invite.GenericGuildInviteEvent;
import net.dv8tion.jda.api.events.guild.invite.GuildInviteCreateEvent;
import net.dv8tion.jda.api.events.guild.invite.GuildInviteDeleteEvent;
import net.dv8tion.jda.api.events.guild.member.*;
import net.dv8tion.jda.api.events.guild.member.update.*;
import net.dv8tion.jda.api.events.guild.override.GenericPermissionOverrideEvent;
import net.dv8tion.jda.api.events.guild.override.PermissionOverrideCreateEvent;
import net.dv8tion.jda.api.events.guild.override.PermissionOverrideDeleteEvent;
import net.dv8tion.jda.api.events.guild.override.PermissionOverrideUpdateEvent;
import net.dv8tion.jda.api.events.guild.scheduledevent.*;
import net.dv8tion.jda.api.events.guild.scheduledevent.update.*;
import net.dv8tion.jda.api.events.guild.update.*;
import net.dv8tion.jda.api.events.guild.voice.*;
import net.dv8tion.jda.api.events.http.HttpRequestEvent;
import net.dv8tion.jda.api.events.interaction.GenericAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.events.interaction.GenericInteractionCreateEvent;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.*;
import net.dv8tion.jda.api.events.interaction.component.*;
import net.dv8tion.jda.api.events.message.*;
import net.dv8tion.jda.api.events.message.poll.GenericMessagePollVoteEvent;
import net.dv8tion.jda.api.events.message.poll.MessagePollVoteAddEvent;
import net.dv8tion.jda.api.events.message.poll.MessagePollVoteRemoveEvent;
import net.dv8tion.jda.api.events.message.react.*;
import net.dv8tion.jda.api.events.role.GenericRoleEvent;
import net.dv8tion.jda.api.events.role.RoleCreateEvent;
import net.dv8tion.jda.api.events.role.RoleDeleteEvent;
import net.dv8tion.jda.api.events.role.update.*;
import net.dv8tion.jda.api.events.self.*;
import net.dv8tion.jda.api.events.session.*;
import net.dv8tion.jda.api.events.stage.GenericStageInstanceEvent;
import net.dv8tion.jda.api.events.stage.StageInstanceCreateEvent;
import net.dv8tion.jda.api.events.stage.StageInstanceDeleteEvent;
import net.dv8tion.jda.api.events.stage.update.GenericStageInstanceUpdateEvent;
import net.dv8tion.jda.api.events.stage.update.StageInstanceUpdatePrivacyLevelEvent;
import net.dv8tion.jda.api.events.stage.update.StageInstanceUpdateTopicEvent;
import net.dv8tion.jda.api.events.sticker.GenericGuildStickerEvent;
import net.dv8tion.jda.api.events.sticker.GuildStickerAddedEvent;
import net.dv8tion.jda.api.events.sticker.GuildStickerRemovedEvent;
import net.dv8tion.jda.api.events.sticker.update.*;
import net.dv8tion.jda.api.events.thread.GenericThreadEvent;
import net.dv8tion.jda.api.events.thread.ThreadHiddenEvent;
import net.dv8tion.jda.api.events.thread.ThreadRevealedEvent;
import net.dv8tion.jda.api.events.thread.member.GenericThreadMemberEvent;
import net.dv8tion.jda.api.events.thread.member.ThreadMemberJoinEvent;
import net.dv8tion.jda.api.events.thread.member.ThreadMemberLeaveEvent;
import net.dv8tion.jda.api.events.user.GenericUserEvent;
import net.dv8tion.jda.api.events.user.UserActivityEndEvent;
import net.dv8tion.jda.api.events.user.UserActivityStartEvent;
import net.dv8tion.jda.api.events.user.UserTypingEvent;
import net.dv8tion.jda.api.events.user.update.*;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.meawmere.jem.JEM;
import net.meawmere.jem.domain.Event;
import net.meawmere.jem.domain.EventType;
import net.meawmere.jem.infrastructure.repositories.EventRepository;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;

public class Handler extends ListenerAdapter {
    private final EventRepository repository;

    public Handler(EventRepository repository) {
        this.repository = repository;
    }

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        invoke(EventType.Ready, event);
    }

    @Override
    public void onGenericEvent(GenericEvent event) {
        invoke(EventType.Generic, event);
    }

    @Override
    public void onGenericUpdate(UpdateEvent<?, ?> event) {
        invoke(EventType.GenericUpdate, event);
    }

    @Override
    public void onRawGateway(RawGatewayEvent event) {
        invoke(EventType.RawGateway, event);
    }

    @Override
    public void onGatewayPing(GatewayPingEvent event) {
        invoke(EventType.GatewayPing, event);
    }

    @Override
    public void onSessionInvalidate(SessionInvalidateEvent event) {
        invoke(EventType.SessionInvalidate, event);
    }

    @Override
    public void onSessionDisconnect(SessionDisconnectEvent event) {
        invoke(EventType.SessionDisconnect, event);
    }

    @Override
    public void onSessionResume(SessionResumeEvent event) {
        invoke(EventType.SessionResume, event);
    }

    @Override
    public void onSessionRecreate(SessionRecreateEvent event) {
        invoke(EventType.SessionRecreate, event);
    }

    @Override
    public void onShutdown(ShutdownEvent event) {
        invoke(EventType.Shutdown, event);
    }

    @Override
    public void onStatusChange(StatusChangeEvent event) {
        invoke(EventType.StatusChange, event);
    }

    @Override
    public void onException(ExceptionEvent event) {
        invoke(EventType.Exception, event);
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        invoke(EventType.SlashCommandInteraction, event);
    }

    @Override
    public void onUserContextInteraction(UserContextInteractionEvent event) {
        invoke(EventType.UserContextInteraction, event);
    }

    @Override
    public void onMessageContextInteraction(MessageContextInteractionEvent event) {
        invoke(EventType.MessageContextInteraction, event);
    }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        invoke(EventType.ButtonInteraction, event);
    }

    @Override
    public void onCommandAutoCompleteInteraction(CommandAutoCompleteInteractionEvent event) {
        invoke(EventType.CommandAutoCompleteInteraction, event);
    }

    @Override
    public void onModalInteraction(ModalInteractionEvent event) {
        invoke(EventType.ModalInteraction, event);
    }

    @Override
    public void onStringSelectInteraction(StringSelectInteractionEvent event) {
        invoke(EventType.StringSelectInteraction, event);
    }

    @Override
    public void onEntitySelectInteraction(EntitySelectInteractionEvent event) {
        invoke(EventType.EntitySelectInteraction, event);
    }

    @Override
    public void onUserUpdateName(UserUpdateNameEvent event) {
        invoke(EventType.UserUpdateName, event);
    }

    @Override
    public void onUserUpdateGlobalName(UserUpdateGlobalNameEvent event) {
        invoke(EventType.UserUpdateGlobalName, event);
    }

    @Override
    public void onUserUpdateDiscriminator(UserUpdateDiscriminatorEvent event) {
        invoke(EventType.UserUpdateDiscriminator, event);
    }

    @Override
    public void onUserUpdateAvatar(UserUpdateAvatarEvent event) {
        invoke(EventType.UserUpdateAvatar, event);
    }

    @Override
    public void onUserUpdateOnlineStatus(UserUpdateOnlineStatusEvent event) {
        invoke(EventType.UserUpdateOnlineStatus, event);
    }

    @Override
    public void onUserUpdateActivityOrder(UserUpdateActivityOrderEvent event) {
        invoke(EventType.UserUpdateActivityOrder, event);
    }

    @Override
    public void onUserUpdateFlags(UserUpdateFlagsEvent event) {
        invoke(EventType.UserUpdateFlags, event);
    }

    @Override
    public void onUserTyping(UserTypingEvent event) {
        invoke(EventType.UserTyping, event);
    }

    @Override
    public void onUserActivityStart(UserActivityStartEvent event) {
        invoke(EventType.UserActivityStart, event);
    }

    @Override
    public void onUserActivityEnd(UserActivityEndEvent event) {
        invoke(EventType.UserActivityEnd, event);
    }

    @Override
    public void onUserUpdateActivities(UserUpdateActivitiesEvent event) {
        invoke(EventType.UserUpdateActivities, event);
    }

    @Override
    public void onSelfUpdateAvatar(SelfUpdateAvatarEvent event) {
        invoke(EventType.SelfUpdateAvatar, event);
    }

    @Override
    public void onSelfUpdateMFA(SelfUpdateMFAEvent event) {
        invoke(EventType.SelfUpdateMFA, event);
    }

    @Override
    public void onSelfUpdateName(SelfUpdateNameEvent event) {
        invoke(EventType.SelfUpdateName, event);
    }

    @Override
    public void onSelfUpdateDiscriminator(SelfUpdateDiscriminatorEvent event) {
        invoke(EventType.SelfUpdateDiscriminator, event);
    }

    @Override
    public void onSelfUpdateGlobalName(SelfUpdateGlobalNameEvent event) {
        invoke(EventType.SelfUpdateGlobalName, event);
    }

    @Override
    public void onSelfUpdateVerified(SelfUpdateVerifiedEvent event) {
        invoke(EventType.SelfUpdateVerified, event);
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        invoke(EventType.MessageReceived, event);
    }

    @Override
    public void onMessageUpdate(MessageUpdateEvent event) {
        invoke(EventType.MessageUpdate, event);
    }

    @Override
    public void onMessageDelete(MessageDeleteEvent event) {
        invoke(EventType.MessageDelete, event);
    }

    @Override
    public void onMessageBulkDelete(MessageBulkDeleteEvent event) {
        invoke(EventType.MessageBulkDelete, event);
    }

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {
        invoke(EventType.MessageReactionAdd, event);
    }

    @Override
    public void onMessageReactionRemove(MessageReactionRemoveEvent event) {
        invoke(EventType.MessageReactionRemove, event);
    }

    @Override
    public void onMessageReactionRemoveAll(MessageReactionRemoveAllEvent event) {
        invoke(EventType.MessageReactionRemoveAll, event);
    }

    @Override
    public void onMessageReactionRemoveEmoji(MessageReactionRemoveEmojiEvent event) {
        invoke(EventType.MessageReactionRemoveEmoji, event);
    }

    @Override
    public void onMessagePollVoteAdd(MessagePollVoteAddEvent event) {
        invoke(EventType.MessagePollVoteAdd, event);
    }

    @Override
    public void onMessagePollVoteRemove(MessagePollVoteRemoveEvent event) {
        invoke(EventType.MessagePollVoteRemove, event);
    }

    @Override
    public void onPermissionOverrideDelete(PermissionOverrideDeleteEvent event) {
        invoke(EventType.PermissionOverrideDelete, event);
    }

    @Override
    public void onPermissionOverrideUpdate(PermissionOverrideUpdateEvent event) {
        invoke(EventType.PermissionOverrideUpdate, event);
    }

    @Override
    public void onPermissionOverrideCreate(PermissionOverrideCreateEvent event) {
        invoke(EventType.PermissionOverrideCreate, event);
    }

    @Override
    public void onStageInstanceDelete(StageInstanceDeleteEvent event) {
        invoke(EventType.StageInstanceDelete, event);
    }

    @Override
    public void onStageInstanceUpdateTopic(StageInstanceUpdateTopicEvent event) {
        invoke(EventType.StageInstanceUpdateTopic, event);
    }

    @Override
    public void onStageInstanceUpdatePrivacyLevel(StageInstanceUpdatePrivacyLevelEvent event) {
        invoke(EventType.StageInstanceUpdatePrivacyLevel, event);
    }

    @Override
    public void onStageInstanceCreate(StageInstanceCreateEvent event) {
        invoke(EventType.StageInstanceCreate, event);
    }

    @Override
    public void onChannelCreate(ChannelCreateEvent event) {
        invoke(EventType.ChannelCreate, event);
    }

    @Override
    public void onChannelDelete(ChannelDeleteEvent event) {
        invoke(EventType.ChannelDelete, event);
    }

    @Override
    public void onChannelUpdateBitrate(ChannelUpdateBitrateEvent event) {
        invoke(EventType.ChannelUpdateBitrate, event);
    }

    @Override
    public void onChannelUpdateName(ChannelUpdateNameEvent event) {
        invoke(EventType.ChannelUpdateName, event);
    }

    @Override
    public void onChannelUpdateFlags(ChannelUpdateFlagsEvent event) {
        invoke(EventType.ChannelUpdateFlags, event);
    }

    @Override
    public void onChannelUpdateNSFW(ChannelUpdateNSFWEvent event) {
        invoke(EventType.ChannelUpdateNSFW, event);
    }

    @Override
    public void onChannelUpdateParent(ChannelUpdateParentEvent event) {
        invoke(EventType.ChannelUpdateParent, event);
    }

    @Override
    public void onChannelUpdatePosition(ChannelUpdatePositionEvent event) {
        invoke(EventType.ChannelUpdatePosition, event);
    }

    @Override
    public void onChannelUpdateRegion(ChannelUpdateRegionEvent event) {
        invoke(EventType.ChannelUpdateRegion, event);
    }

    @Override
    public void onChannelUpdateSlowmode(ChannelUpdateSlowmodeEvent event) {
        invoke(EventType.ChannelUpdateSlowmode, event);
    }

    @Override
    public void onChannelUpdateDefaultThreadSlowmode(ChannelUpdateDefaultThreadSlowmodeEvent event) {
        invoke(EventType.ChannelUpdateDefaultThreadSlowmode, event);
    }

    @Override
    public void onChannelUpdateDefaultReaction(ChannelUpdateDefaultReactionEvent event) {
        invoke(EventType.ChannelUpdateDefaultReaction, event);
    }

    @Override
    public void onChannelUpdateDefaultSortOrder(ChannelUpdateDefaultSortOrderEvent event) {
        invoke(EventType.ChannelUpdateDefaultSortOrder, event);
    }

    @Override
    public void onChannelUpdateDefaultLayout(ChannelUpdateDefaultLayoutEvent event) {
        invoke(EventType.ChannelUpdateDefaultLayout, event);
    }

    @Override
    public void onChannelUpdateTopic(ChannelUpdateTopicEvent event) {
        invoke(EventType.ChannelUpdateTopic, event);
    }

    @Override
    public void onChannelUpdateVoiceStatus(ChannelUpdateVoiceStatusEvent event) {
        invoke(EventType.ChannelUpdateVoiceStatus, event);
    }

    @Override
    public void onChannelUpdateType(ChannelUpdateTypeEvent event) {
        invoke(EventType.ChannelUpdateType, event);
    }

    @Override
    public void onChannelUpdateUserLimit(ChannelUpdateUserLimitEvent event) {
        invoke(EventType.ChannelUpdateUserLimit, event);
    }

    @Override
    public void onChannelUpdateArchived(ChannelUpdateArchivedEvent event) {
        invoke(EventType.ChannelUpdateArchived, event);
    }

    @Override
    public void onChannelUpdateArchiveTimestamp(ChannelUpdateArchiveTimestampEvent event) {
        invoke(EventType.ChannelUpdateArchiveTimestamp, event);
    }

    @Override
    public void onChannelUpdateAutoArchiveDuration(ChannelUpdateAutoArchiveDurationEvent event) {
        invoke(EventType.ChannelUpdateAutoArchiveDuration, event);
    }

    @Override
    public void onChannelUpdateLocked(ChannelUpdateLockedEvent event) {
        invoke(EventType.ChannelUpdateLocked, event);
    }

    @Override
    public void onChannelUpdateInvitable(ChannelUpdateInvitableEvent event) {
        invoke(EventType.ChannelUpdateInvitable, event);
    }

    @Override
    public void onChannelUpdateAppliedTags(ChannelUpdateAppliedTagsEvent event) {
        invoke(EventType.ChannelUpdateAppliedTags, event);
    }

    @Override
    public void onForumTagAdd(ForumTagAddEvent event) {
        invoke(EventType.ForumTagAdd, event);
    }

    @Override
    public void onForumTagRemove(ForumTagRemoveEvent event) {
        invoke(EventType.ForumTagRemove, event);
    }

    @Override
    public void onForumTagUpdateName(ForumTagUpdateNameEvent event) {
        invoke(EventType.ForumTagUpdateName, event);
    }

    @Override
    public void onForumTagUpdateEmoji(ForumTagUpdateEmojiEvent event) {
        invoke(EventType.ForumTagUpdateEmoji, event);
    }

    @Override
    public void onForumTagUpdateModerated(ForumTagUpdateModeratedEvent event) {
        invoke(EventType.ForumTagUpdateModerated, event);
    }

    @Override
    public void onThreadRevealed(ThreadRevealedEvent event) {
        invoke(EventType.ThreadRevealed, event);
    }

    @Override
    public void onThreadHidden(ThreadHiddenEvent event) {
        invoke(EventType.ThreadHidden, event);
    }

    @Override
    public void onThreadMemberJoin(ThreadMemberJoinEvent event) {
        invoke(EventType.ThreadMemberJoin, event);
    }

    @Override
    public void onThreadMemberLeave(ThreadMemberLeaveEvent event) {
        invoke(EventType.ThreadMemberLeave, event);
    }

    @Override
    public void onGuildReady(GuildReadyEvent event) {
        invoke(EventType.GuildReady, event);
    }

    @Override
    public void onGuildTimeout(GuildTimeoutEvent event) {
        invoke(EventType.GuildTimeout, event);
    }

    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        invoke(EventType.GuildJoin, event);
    }

    @Override
    public void onGuildLeave(GuildLeaveEvent event) {
        invoke(EventType.GuildLeave, event);
    }

    @Override
    public void onGuildAvailable(GuildAvailableEvent event) {
        invoke(EventType.GuildAvailable, event);
    }

    @Override
    public void onGuildUnavailable(GuildUnavailableEvent event) {
        invoke(EventType.GuildUnavailable, event);
    }

    @Override
    public void onUnavailableGuildJoined(UnavailableGuildJoinedEvent event) {
        invoke(EventType.UnavailableGuildJoined, event);
    }

    @Override
    public void onUnavailableGuildLeave(UnavailableGuildLeaveEvent event) {
        invoke(EventType.UnavailableGuildLeave, event);
    }

    @Override
    public void onGuildBan(GuildBanEvent event) {
        invoke(EventType.GuildBan, event);
    }

    @Override
    public void onGuildUnban(GuildUnbanEvent event) {
        invoke(EventType.GuildUnban, event);
    }

    @Override
    public void onGuildAuditLogEntryCreate(GuildAuditLogEntryCreateEvent event) {
        invoke(EventType.GuildAuditLogEntryCreate, event);
    }

    @Override
    public void onGuildMemberRemove(GuildMemberRemoveEvent event) {
        invoke(EventType.GuildMemberRemove, event);
    }

    @Override
    public void onGuildUpdateAfkChannel(GuildUpdateAfkChannelEvent event) {
        invoke(EventType.GuildUpdateAfkChannel, event);
    }

    @Override
    public void onGuildUpdateSystemChannel(GuildUpdateSystemChannelEvent event) {
        invoke(EventType.GuildUpdateSystemChannel, event);
    }

    @Override
    public void onGuildUpdateRulesChannel(GuildUpdateRulesChannelEvent event) {
        invoke(EventType.GuildUpdateRulesChannel, event);
    }

    @Override
    public void onGuildUpdateCommunityUpdatesChannel(GuildUpdateCommunityUpdatesChannelEvent event) {
        invoke(EventType.GuildUpdateCommunityUpdatesChannel, event);
    }

    @Override
    public void onGuildUpdateSafetyAlertsChannel(GuildUpdateSafetyAlertsChannelEvent event) {
        invoke(EventType.GuildUpdateSafetyAlertsChannel, event);
    }

    @Override
    public void onGuildUpdateAfkTimeout(GuildUpdateAfkTimeoutEvent event) {
        invoke(EventType.GuildUpdateAfkTimeout, event);
    }

    @Override
    public void onGuildUpdateSecurityIncidentActions(GuildUpdateSecurityIncidentActionsEvent event) {
        invoke(EventType.GuildUpdateSecurityIncidentActions, event);
    }

    @Override
    public void onGuildUpdateSecurityIncidentDetections(GuildUpdateSecurityIncidentDetectionsEvent event) {
        invoke(EventType.GuildUpdateSecurityIncidentDetections, event);
    }

    @Override
    public void onGuildUpdateExplicitContentLevel(GuildUpdateExplicitContentLevelEvent event) {
        invoke(EventType.GuildUpdateExplicitContentLevel, event);
    }

    @Override
    public void onGuildUpdateIcon(GuildUpdateIconEvent event) {
        invoke(EventType.GuildUpdateIcon, event);
    }

    @Override
    public void onGuildUpdateMFALevel(GuildUpdateMFALevelEvent event) {
        invoke(EventType.GuildUpdateMFALevel, event);
    }

    @Override
    public void onGuildUpdateName(GuildUpdateNameEvent event) {
        invoke(EventType.GuildUpdateName, event);
    }

    @Override
    public void onGuildUpdateNotificationLevel(GuildUpdateNotificationLevelEvent event) {
        invoke(EventType.GuildUpdateNotificationLevel, event);
    }

    @Override
    public void onGuildUpdateOwner(GuildUpdateOwnerEvent event) {
        invoke(EventType.GuildUpdateOwner, event);
    }

    @Override
    public void onGuildUpdateSplash(GuildUpdateSplashEvent event) {
        invoke(EventType.GuildUpdateSplash, event);
    }

    @Override
    public void onGuildUpdateVerificationLevel(GuildUpdateVerificationLevelEvent event) {
        invoke(EventType.GuildUpdateVerificationLevel, event);
    }

    @Override
    public void onGuildUpdateLocale(GuildUpdateLocaleEvent event) {
        invoke(EventType.GuildUpdateLocale, event);
    }

    @Override
    public void onGuildUpdateFeatures(GuildUpdateFeaturesEvent event) {
        invoke(EventType.GuildUpdateFeatures, event);
    }

    @Override
    public void onGuildUpdateVanityCode(GuildUpdateVanityCodeEvent event) {
        invoke(EventType.GuildUpdateVanityCode, event);
    }

    @Override
    public void onGuildUpdateBanner(GuildUpdateBannerEvent event) {
        invoke(EventType.GuildUpdateBanner, event);
    }

    @Override
    public void onGuildUpdateDescription(GuildUpdateDescriptionEvent event) {
        invoke(EventType.GuildUpdateDescription, event);
    }

    @Override
    public void onGuildUpdateBoostTier(GuildUpdateBoostTierEvent event) {
        invoke(EventType.GuildUpdateBoostTier, event);
    }

    @Override
    public void onGuildUpdateBoostCount(GuildUpdateBoostCountEvent event) {
        invoke(EventType.GuildUpdateBoostCount, event);
    }

    @Override
    public void onGuildUpdateMaxMembers(GuildUpdateMaxMembersEvent event) {
        invoke(EventType.GuildUpdateMaxMembers, event);
    }

    @Override
    public void onGuildUpdateMaxPresences(GuildUpdateMaxPresencesEvent event) {
        invoke(EventType.GuildUpdateMaxPresences, event);
    }

    @Override
    public void onGuildUpdateNSFWLevel(GuildUpdateNSFWLevelEvent event) {
        invoke(EventType.GuildUpdateNSFWLevel, event);
    }

    @Override
    public void onScheduledEventUpdateDescription(ScheduledEventUpdateDescriptionEvent event) {
        invoke(EventType.ScheduledEventUpdateDescription, event);
    }

    @Override
    public void onScheduledEventUpdateEndTime(ScheduledEventUpdateEndTimeEvent event) {
        invoke(EventType.ScheduledEventUpdateEndTime, event);
    }

    @Override
    public void onScheduledEventUpdateLocation(ScheduledEventUpdateLocationEvent event) {
        invoke(EventType.ScheduledEventUpdateLocation, event);
    }

    @Override
    public void onScheduledEventUpdateName(ScheduledEventUpdateNameEvent event) {
        invoke(EventType.ScheduledEventUpdateName, event);
    }

    @Override
    public void onScheduledEventUpdateStartTime(ScheduledEventUpdateStartTimeEvent event) {
        invoke(EventType.ScheduledEventUpdateStartTime, event);
    }

    @Override
    public void onScheduledEventUpdateStatus(ScheduledEventUpdateStatusEvent event) {
        invoke(EventType.ScheduledEventUpdateStatus, event);
    }

    @Override
    public void onScheduledEventUpdateImage(ScheduledEventUpdateImageEvent event) {
        invoke(EventType.ScheduledEventUpdateImage, event);
    }

    @Override
    public void onScheduledEventCreate(ScheduledEventCreateEvent event) {
        invoke(EventType.ScheduledEventCreate, event);
    }

    @Override
    public void onScheduledEventDelete(ScheduledEventDeleteEvent event) {
        invoke(EventType.ScheduledEventDelete, event);
    }

    @Override
    public void onScheduledEventUserAdd(ScheduledEventUserAddEvent event) {
        invoke(EventType.ScheduledEventUserAdd, event);
    }

    @Override
    public void onScheduledEventUserRemove(ScheduledEventUserRemoveEvent event) {
        invoke(EventType.ScheduledEventUserRemove, event);
    }

    @Override
    public void onGuildInviteCreate(GuildInviteCreateEvent event) {
        invoke(EventType.GuildInviteCreate, event);
    }

    @Override
    public void onGuildInviteDelete(GuildInviteDeleteEvent event) {
        invoke(EventType.GuildInviteDelete, event);
    }

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        invoke(EventType.GuildMemberJoin, event);
    }

    @Override
    public void onGuildMemberRoleAdd(GuildMemberRoleAddEvent event) {
        invoke(EventType.GuildMemberRoleAdd, event);
    }

    @Override
    public void onGuildMemberRoleRemove(GuildMemberRoleRemoveEvent event) {
        invoke(EventType.GuildMemberRoleRemove, event);
    }

    @Override
    public void onGuildMemberUpdate(GuildMemberUpdateEvent event) {
        invoke(EventType.GuildMemberUpdate, event);
    }

    @Override
    public void onGuildMemberUpdateNickname(GuildMemberUpdateNicknameEvent event) {
        invoke(EventType.GuildMemberUpdateNickname, event);
    }

    @Override
    public void onGuildMemberUpdateAvatar(GuildMemberUpdateAvatarEvent event) {
        invoke(EventType.GuildMemberUpdateAvatar, event);
    }

    @Override
    public void onGuildMemberUpdateBoostTime(GuildMemberUpdateBoostTimeEvent event) {
        invoke(EventType.GuildMemberUpdateBoostTime, event);
    }

    @Override
    public void onGuildMemberUpdatePending(GuildMemberUpdatePendingEvent event) {
        invoke(EventType.GuildMemberUpdatePending, event);
    }

    @Override
    public void onGuildMemberUpdateFlags(GuildMemberUpdateFlagsEvent event) {
        invoke(EventType.GuildMemberUpdateFlags, event);
    }

    @Override
    public void onGuildMemberUpdateTimeOut(GuildMemberUpdateTimeOutEvent event) {
        invoke(EventType.GuildMemberUpdateTimeOut, event);
    }

    @Override
    public void onGuildVoiceUpdate(GuildVoiceUpdateEvent event) {
        invoke(EventType.GuildVoiceUpdate, event);
    }

    @Override
    public void onGuildVoiceMute(GuildVoiceMuteEvent event) {
        invoke(EventType.GuildVoiceMute, event);
    }

    @Override
    public void onGuildVoiceDeafen(GuildVoiceDeafenEvent event) {
        invoke(EventType.GuildVoiceDeafen, event);
    }

    @Override
    public void onGuildVoiceGuildMute(GuildVoiceGuildMuteEvent event) {
        invoke(EventType.GuildVoiceGuildMute, event);
    }

    @Override
    public void onGuildVoiceGuildDeafen(GuildVoiceGuildDeafenEvent event) {
        invoke(EventType.GuildVoiceGuildDeafen, event);
    }

    @Override
    public void onGuildVoiceSelfMute(GuildVoiceSelfMuteEvent event) {
        invoke(EventType.GuildVoiceSelfMute, event);
    }

    @Override
    public void onGuildVoiceSelfDeafen(GuildVoiceSelfDeafenEvent event) {
        invoke(EventType.GuildVoiceSelfDeafen, event);
    }

    @Override
    public void onGuildVoiceSuppress(GuildVoiceSuppressEvent event) {
        invoke(EventType.GuildVoiceSuppress, event);
    }

    @Override
    public void onGuildVoiceStream(GuildVoiceStreamEvent event) {
        invoke(EventType.GuildVoiceStream, event);
    }

    @Override
    public void onGuildVoiceVideo(GuildVoiceVideoEvent event) {
        invoke(EventType.GuildVoiceVideo, event);
    }

    @Override
    public void onGuildVoiceRequestToSpeak(GuildVoiceRequestToSpeakEvent event) {
        invoke(EventType.GuildVoiceRequestToSpeak, event);
    }

    @Override
    public void onAutoModExecution(AutoModExecutionEvent event) {
        invoke(EventType.AutoModExecution, event);
    }

    @Override
    public void onAutoModRuleCreate(AutoModRuleCreateEvent event) {
        invoke(EventType.AutoModRuleCreate, event);
    }

    @Override
    public void onAutoModRuleUpdate(AutoModRuleUpdateEvent event) {
        invoke(EventType.AutoModRuleUpdate, event);
    }

    @Override
    public void onAutoModRuleDelete(AutoModRuleDeleteEvent event) {
        invoke(EventType.AutoModRuleDelete, event);
    }

    @Override
    public void onRoleCreate(RoleCreateEvent event) {
        invoke(EventType.RoleCreate, event);
    }

    @Override
    public void onRoleDelete(RoleDeleteEvent event) {
        invoke(EventType.RoleDelete, event);
    }

    @Override
    public void onRoleUpdateColor(RoleUpdateColorEvent event) {
        invoke(EventType.RoleUpdateColor, event);
    }

    @Override
    public void onRoleUpdateHoisted(RoleUpdateHoistedEvent event) {
        invoke(EventType.RoleUpdateHoisted, event);
    }

    @Override
    public void onRoleUpdateIcon(RoleUpdateIconEvent event) {
        invoke(EventType.RoleUpdateIcon, event);
    }

    @Override
    public void onRoleUpdateMentionable(RoleUpdateMentionableEvent event) {
        invoke(EventType.RoleUpdateMentionable, event);
    }

    @Override
    public void onRoleUpdateName(RoleUpdateNameEvent event) {
        invoke(EventType.RoleUpdateName, event);
    }

    @Override
    public void onRoleUpdatePermissions(RoleUpdatePermissionsEvent event) {
        invoke(EventType.RoleUpdatePermissions, event);
    }

    @Override
    public void onRoleUpdatePosition(RoleUpdatePositionEvent event) {
        invoke(EventType.RoleUpdatePosition, event);
    }

    @Override
    public void onEmojiAdded(EmojiAddedEvent event) {
        invoke(EventType.EmojiAdded, event);
    }

    @Override
    public void onEmojiRemoved(EmojiRemovedEvent event) {
        invoke(EventType.EmojiRemoved, event);
    }

    @Override
    public void onEmojiUpdateName(EmojiUpdateNameEvent event) {
        invoke(EventType.EmojiUpdateName, event);
    }

    @Override
    public void onEmojiUpdateRoles(EmojiUpdateRolesEvent event) {
        invoke(EventType.EmojiUpdateRoles, event);
    }

    @Override
    public void onGenericPrivilegeUpdate(GenericPrivilegeUpdateEvent event) {
        invoke(EventType.GenericPrivilegeUpdate, event);
    }

    @Override
    public void onApplicationCommandUpdatePrivileges(ApplicationCommandUpdatePrivilegesEvent event) {
        invoke(EventType.ApplicationCommandUpdatePrivileges, event);
    }

    @Override
    public void onApplicationUpdatePrivileges(ApplicationUpdatePrivilegesEvent event) {
        invoke(EventType.ApplicationUpdatePrivileges, event);
    }

    @Override
    public void onGuildStickerAdded(GuildStickerAddedEvent event) {
        invoke(EventType.GuildStickerAdded, event);
    }

    @Override
    public void onGuildStickerRemoved(GuildStickerRemovedEvent event) {
        invoke(EventType.GuildStickerRemoved, event);
    }

    @Override
    public void onGuildStickerUpdateName(GuildStickerUpdateNameEvent event) {
        invoke(EventType.GuildStickerUpdateName, event);
    }

    @Override
    public void onGuildStickerUpdateTags(GuildStickerUpdateTagsEvent event) {
        invoke(EventType.GuildStickerUpdateTags, event);
    }

    @Override
    public void onGuildStickerUpdateDescription(GuildStickerUpdateDescriptionEvent event) {
        invoke(EventType.GuildStickerUpdateDescription, event);
    }

    @Override
    public void onGuildStickerUpdateAvailable(GuildStickerUpdateAvailableEvent event) {
        invoke(EventType.GuildStickerUpdateAvailable, event);
    }

    @Override
    public void onEntitlementCreate(EntitlementCreateEvent event) {
        invoke(EventType.EntitlementCreate, event);
    }

    @Override
    public void onEntitlementUpdate(EntitlementUpdateEvent event) {
        invoke(EventType.EntitlementUpdate, event);
    }

    @Override
    public void onEntitlementDelete(EntitlementDeleteEvent event) {
        invoke(EventType.EntitlementDelete, event);
    }

    @Override
    public void onHttpRequest(HttpRequestEvent event) {
        invoke(EventType.HttpRequest, event);
    }

    @Override
    public void onGenericSession(GenericSessionEvent event) {
        invoke(EventType.GenericSession, event);
    }

    @Override
    public void onGenericInteractionCreate(GenericInteractionCreateEvent event) {
        invoke(EventType.GenericInteractionCreate, event);
    }

    @Override
    public void onGenericAutoCompleteInteraction(GenericAutoCompleteInteractionEvent event) {
        invoke(EventType.GenericAutoCompleteInteraction, event);
    }

    @Override
    public void onGenericComponentInteractionCreate(GenericComponentInteractionCreateEvent event) {
        invoke(EventType.GenericComponentInteractionCreate, event);
    }

    @Override
    public void onGenericCommandInteraction(GenericCommandInteractionEvent event) {
        invoke(EventType.GenericCommandInteraction, event);
    }

    @Override
    public void onGenericContextInteraction(GenericContextInteractionEvent<?> event) {
        invoke(EventType.GenericContextInteraction, event);
    }

    @Override
    public void onGenericSelectMenuInteraction(GenericSelectMenuInteractionEvent event) {
        invoke(EventType.GenericSelectMenuInteraction, event);
    }

    @Override
    public void onGenericMessage(GenericMessageEvent event) {
        invoke(EventType.GenericMessage, event);
    }

    @Override
    public void onGenericMessageReaction(GenericMessageReactionEvent event) {
        invoke(EventType.GenericMessageReaction, event);
    }

    @Override
    public void onGenericMessagePollVote(GenericMessagePollVoteEvent event) {
        invoke(EventType.GenericMessagePollVote, event);
    }

    @Override
    public void onGenericUser(GenericUserEvent event) {
        invoke(EventType.GenericUser, event);
    }

    @Override
    public void onGenericUserPresence(GenericUserPresenceEvent event) {
        invoke(EventType.GenericUserPresence, event);
    }

    @Override
    public void onGenericUserUpdate(GenericUserUpdateEvent event) {
        invoke(EventType.GenericUserUpdate, event);
    }

    @Override
    public void onGenericSelfUpdate(GenericSelfUpdateEvent event) {
        invoke(EventType.GenericSelfUpdate, event);
    }

    @Override
    public void onGenericStageInstance(GenericStageInstanceEvent event) {
        invoke(EventType.GenericStageInstance, event);
    }

    @Override
    public void onGenericStageInstanceUpdate(GenericStageInstanceUpdateEvent event) {
        invoke(EventType.GenericStageInstanceUpdate, event);
    }

    @Override
    public void onGenericChannel(GenericChannelEvent event) {
        invoke(EventType.GenericChannel, event);
    }

    @Override
    public void onGenericChannelUpdate(GenericChannelUpdateEvent<?> event) {
        invoke(EventType.GenericChannelUpdate, event);
    }

    @Override
    public void onGenericThread(GenericThreadEvent event) {
        invoke(EventType.GenericThread, event);
    }

    @Override
    public void onGenericThreadMember(GenericThreadMemberEvent event) {
        invoke(EventType.GenericThreadMember, event);
    }

    @Override
    public void onGenericGuild(GenericGuildEvent event) {
        invoke(EventType.GenericGuild, event);
    }

    @Override
    public void onGenericGuildUpdate(GenericGuildUpdateEvent event) {
        invoke(EventType.GenericGuildUpdate, event);
    }

    @Override
    public void onGenericGuildInvite(GenericGuildInviteEvent event) {
        invoke(EventType.GenericGuildInvite, event);
    }

    @Override
    public void onGenericGuildMember(GenericGuildMemberEvent event) {
        invoke(EventType.GenericGuildMember, event);
    }

    @Override
    public void onGenericGuildMemberUpdate(GenericGuildMemberUpdateEvent event) {
        invoke(EventType.GenericGuildMemberUpdate, event);
    }

    @Override
    public void onGenericGuildVoice(GenericGuildVoiceEvent event) {
        invoke(EventType.GenericGuildVoice, event);
    }

    @Override
    public void onGenericAutoModRule(GenericAutoModRuleEvent event) {
        invoke(EventType.GenericAutoModRule, event);
    }

    @Override
    public void onGenericRole(GenericRoleEvent event) {
        invoke(EventType.GenericRole, event);
    }

    @Override
    public void onGenericRoleUpdate(GenericRoleUpdateEvent event) {
        invoke(EventType.GenericRoleUpdate, event);
    }

    @Override
    public void onGenericEmoji(GenericEmojiEvent event) {
        invoke(EventType.GenericEmoji, event);
    }

    @Override
    public void onGenericEmojiUpdate(GenericEmojiUpdateEvent event) {
        invoke(EventType.GenericEmojiUpdate, event);
    }

    @Override
    public void onGenericGuildSticker(GenericGuildStickerEvent event) {
        invoke(EventType.GenericGuildSticker, event);
    }

    @Override
    public void onGenericGuildStickerUpdate(GenericGuildStickerUpdateEvent event) {
        invoke(EventType.GenericGuildStickerUpdate, event);
    }

    @Override
    public void onGenericEntitlement(GenericEntitlementEvent event) {
        invoke(EventType.GenericEntitlement, event);
    }

    @Override
    public void onGenericPermissionOverride(GenericPermissionOverrideEvent event) {
        invoke(EventType.GenericPermissionOverride, event);
    }

    @Override
    public void onGenericScheduledEventUpdate(GenericScheduledEventUpdateEvent event) {
        invoke(EventType.GenericScheduledEventUpdate, event);
    }

    @Override
    public void onGenericScheduledEventGateway(GenericScheduledEventGatewayEvent event) {
        invoke(EventType.GenericScheduledEventGateway, event);
    }

    @Override
    public void onGenericScheduledEventUser(GenericScheduledEventUserEvent event) {
        invoke(EventType.GenericScheduledEventUser, event);
    }

    @Override
    public void onGenericForumTag(GenericForumTagEvent event) {
        invoke(EventType.GenericForumTag, event);
    }

    @Override
    public void onGenericForumTagUpdate(GenericForumTagUpdateEvent event) {
        invoke(EventType.GenericForumTagUpdate, event);
    }

    private void invoke(EventType type, Object... objs) {
        for (Event event_entity : repository.getEvents()) {
            if (event_entity.eventType() == type) {
                try {
                    event_entity.invoke().invoke(objs);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
