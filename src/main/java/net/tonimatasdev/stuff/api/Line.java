package net.tonimatasdev.stuff.api;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

import java.util.Objects;

public record Line(Location location1, Location location2) {
    public void generateDustLine(Color color) {
        double distance = location1.distance(location2);

        Vector vector1 = location1.toVector();
        Vector vector2 = location2.toVector();

        Vector vector = vector1.clone().subtract(vector2).normalize().multiply(0.25);
        double length = 0.0;

        while (length < distance) {
            Location now = new Location(location1.getWorld(), vector2.getX(), vector2.getY(), vector2.getZ());
            Particle.DustOptions dustOptions = new Particle.DustOptions(color, 1.0F);
            Objects.requireNonNull(location1.getWorld()).spawnParticle(Particle.REDSTONE, now, 1, 0, 0, 0, dustOptions);

            length += 0.25;

            vector2.add(vector);
        }
    }

    public void generateLine(Particle particle) {
        double distance = location1.distance(location2);

        Vector vector1 = location1.toVector();
        Vector vector2 = location2.toVector();

        Vector vector = vector1.clone().subtract(vector2).normalize().multiply(0.25);
        double length = 0.0;

        while (length < distance) {
            Location now = new Location(location1.getWorld(), vector2.getX(), vector2.getY(), vector2.getZ());
            Objects.requireNonNull(location1.getWorld()).spawnParticle(particle, now, 1, 0, 0, 0);

            length += 0.25;

            vector2.add(vector);
        }
    }
}
