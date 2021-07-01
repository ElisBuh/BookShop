package com.senla.util.serialization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class Serialize {
    private static final Logger log = LoggerFactory.getLogger(Serialize.class);
    private final String path;
    private final File file;

    public Serialize(final String path) {
        this.path = path;
        this.file = new File(path);
    }

    public void serialize(final Object object) {
        log.info("Сериализация");
        try (final FileOutputStream FileOutputStream = new FileOutputStream(path);
             final ObjectOutputStream ObjectOutputStream = new ObjectOutputStream(FileOutputStream)) {
            ObjectOutputStream.writeObject(object);
        } catch (final IOException io) {
            log.error(io.toString());
        }
    }

    public <T> T deserialize() {
        try (final FileInputStream FileOutputStream = new FileInputStream(path);
             final ObjectInputStream ObjectInputStream = new ObjectInputStream(FileOutputStream)) {
            final Object Object = ObjectInputStream.readObject();
            final Class<?> Class = Object.getClass();
            return (T) Class.cast(Object);
        } catch (final IOException | ClassNotFoundException ex) {
            log.info(ex.toString());
            return null;
        }
    }

    public boolean is() {
        return this.file.exists();
    }
}
