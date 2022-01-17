//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.terrar.bobr.modules.render;

import java.lang.invoke.*;
import java.lang.reflect.*;
import net.minecraft.client.*;
import net.minecraft.util.*;
import net.minecraft.client.renderer.entity.*;

public class ReflectionHelper
{
    public static MethodHandles.Lookup lookup;
    
    public static Field getField(final Class class_, final String... arrstring) {
        for (final Field field : class_.getDeclaredFields()) {
            field.setAccessible(true);
            for (final String string : arrstring) {
                if (field.getName().equals(string)) {
                    return field;
                }
            }
        }
        return null;
    }
    
    public static MethodHandle getLookupMethod(final Class class_, final MethodType methodType, final String... arrstring) throws Throwable {
        for (final String string : arrstring) {
            final MethodHandle methodHandle = lookup().findVirtual(class_, string, methodType);
            if (methodHandle != null) {
                return methodHandle;
            }
        }
        return null;
    }
    
    public static Method getMethod(final Class class_, final String... arrstring) {
        for (final Method method : class_.getDeclaredMethods()) {
            method.setAccessible(true);
            for (final String string : arrstring) {
                if (method.getName().equals(string)) {
                    return method;
                }
            }
        }
        return null;
    }
    
    public static float getPartialTicks() throws IllegalAccessException, Throwable {
        final Field field = getField(Minecraft.class, "timer", "timer", "Y");
        final Timer timer = lookup().unreflectGetter(field).invoke(Minecraft.getMinecraft());
        return timer.renderPartialTicks;
    }
    
    public static double getRenderPosX() throws Throwable, IllegalAccessException {
        return lookup().unreflectGetter(getField(RenderManager.class, "renderPosX", "renderPosX", "o")).invoke(Minecraft.getMinecraft().getRenderManager());
    }
    
    public static double getRenderPosY() throws IllegalAccessException, Throwable {
        return lookup().unreflectGetter(getField(RenderManager.class, "renderPosY", "renderPosY", "p")).invoke(Minecraft.getMinecraft().getRenderManager());
    }
    
    public static double getRenderPosZ() throws IllegalAccessException, Throwable {
        return lookup().unreflectGetter(getField(RenderManager.class, "renderPosZ", "renderPosZ", "q")).invoke(Minecraft.getMinecraft().getRenderManager());
    }
    
    public static void hookField(final Field field, final Object object, final Object object2) throws IllegalAccessException, Throwable {
        lookup().unreflectSetter(field).invoke(object, object2);
    }
    
    public static Object invoke(final Method method, final Object... arrobject) throws Throwable {
        return lookup().unreflect(method).invoke(arrobject);
    }
    
    public static MethodHandles.Lookup lookup() {
        return ReflectionHelper.lookup;
    }
    
    public static MethodHandle unreflect(final Method method) throws IllegalAccessException {
        return lookup().unreflect(method);
    }
}
