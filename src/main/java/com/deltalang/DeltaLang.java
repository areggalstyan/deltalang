package com.deltalang;

import com.deltalang.error.ErrorHandler;
import com.deltalang.io.*;
import com.google.gson.*;
import lombok.Cleanup;
import lombok.SneakyThrows;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;

public class DeltaLang {
    private static final OutputStream output = new StandardOutput();
    private static final InputStream input = new StandardInput();
    private static final OutputStream error = new ErrorOutput();
    private static final ErrorHandler handler = new ErrorHandler(error);
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().serializeNulls()
            .registerTypeAdapter(Sequence.class, new SequenceSerializer()).create();

    @SneakyThrows
    public static void main(String[] args) {
        if (args.length != 1 && args.length != 2) return;

        var characters = Sequence.of(Path.of(args[0]));
        var tokens = new Scanner(characters, handler).scan();
        var statements = new Parser(tokens, handler).parse();

        if (args.length == 2) {
            @Cleanup
            var writer = Files.newBufferedWriter(Path.of(args[1]));
            gson.toJson(statements, writer);
        }

        new Executor(handler, output, input).execute(statements);
    }

    private static class SequenceSerializer implements JsonSerializer<Sequence<?>> {
        @Override
        public JsonElement serialize(Sequence<?> src, Type typeOfSrc, JsonSerializationContext context) {
            var array = new JsonArray();
            var copy = src.copy();

            while (copy.hasNext()) {
                array.add(context.serialize(copy.consume()));
            }

            return array;
        }
    }
}
