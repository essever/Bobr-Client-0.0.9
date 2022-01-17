//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.managers;

import ru.terrar.bobr.util.*;
import java.util.*;

public class FriendManager
{
    public static List<String> FRIENDS;
    
    public static void toggleFriend(final String nick) {
        if (isFriend(nick)) {
            FriendManager.FRIENDS.remove(nick);
            ChatUtil.clientMessage(nick + " is removed from Friend list.");
        }
        else {
            FriendManager.FRIENDS.add(nick);
            ChatUtil.clientMessage(nick + " is add in Friend list.");
        }
    }
    
    public static boolean isFriend(final String nick) {
        for (final String friend : FriendManager.FRIENDS) {
            if (friend.equalsIgnoreCase(nick)) {
                return true;
            }
        }
        return false;
    }
    
    static {
        FriendManager.FRIENDS = new ArrayList<String>();
    }
}
