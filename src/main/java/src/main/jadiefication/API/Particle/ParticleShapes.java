package src.main.jadiefication.API.Particle;

import com.destroystokyo.paper.ParticleBuilder;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.plugin.Plugin;

public class ParticleShapes {

    public static void createSphere(int size, Player player) {
        Location location = player.getLocation();
        ParticleBuilder builder = new ParticleBuilder(Particle.WHITE_SMOKE)
                .count(1)
                .offset(0, 0, 0)
                .extra(0)
                .force(true);

        for (double i = 0; i <= Math.PI; i += Math.PI / size) {
            double radius = Math.sin(i);
            double y = Math.cos(i);
            for (double a = 0; a < Math.PI * 2; a += Math.PI / size) {
                double x = Math.cos(a) * radius;
                double z = Math.sin(a) * radius;
                Location particleLocation = location.clone().add(x * size, y * size, z * size);
                builder.location(particleLocation).spawn();
            }
        }
    }

    public static void createCircle(int radius, Player player) {
        Location center = player.getLocation();
        ParticleBuilder builder = new ParticleBuilder(Particle.WHITE_SMOKE)
                .count(1)
                .offset(0, 0, 0)
                .extra(0)
                .force(true);

        for (double angle = 0; angle < 2 * Math.PI; angle += Math.PI / 16) {
            double x = radius * Math.cos(angle);
            double z = radius * Math.sin(angle);
            Location particleLocation = center.clone().add(x, 0, z);
            builder.location(particleLocation).spawn();
        }
    }

    public static void createSquare(int size, Player player) {
        Location center = player.getLocation();
        ParticleBuilder builder = new ParticleBuilder(Particle.WHITE_SMOKE)
            .count(1)
            .offset(0, 0, 0)
            .extra(0);

        for (int i = -size; i <= size; i++) {
            builder.location(center.clone().add(i, 0, -size)).spawn();
            builder.location(center.clone().add(i, 0, size)).spawn();
            builder.location(center.clone().add(-size, 0, i)).spawn();
            builder.location(center.clone().add(size, 0, i)).spawn();
        }
    }

    public static void createHelix(int height, double radius, Player player) {
        Location center = player.getLocation();
        ParticleBuilder builder = new ParticleBuilder(Particle.WHITE_SMOKE)
            .count(1)
            .offset(0, 0, 0)
            .extra(0);

        for (double y = 0; y < height; y += 0.1) {
            double angle = y * 2 * Math.PI / 3;
            double x = radius * Math.cos(angle);
            double z = radius * Math.sin(angle);
            builder.location(center.clone().add(x, y, z)).spawn();
        }
    }

    public static void createOrbit(Plugin plugin, Player player, int radius) {
        new BukkitRunnable() {
            double angle = 0;
            public void run() {
                ParticleBuilder builder = new ParticleBuilder(Particle.WHITE_SMOKE)
                    .count(1)
                    .offset(0, 0, 0)
                    .extra(0);

                double x = radius * Math.cos(angle);
                double z = radius * Math.sin(angle);
                Location particleLocation = player.getLocation().add(x, 1.5, z);
                builder.location(particleLocation).spawn();

                angle += Math.PI / 16;
                if (angle >= 2 * Math.PI) {
                    angle = 0;
                }
            }
        }.runTaskTimer(plugin, 0L, 1L);
    }
}