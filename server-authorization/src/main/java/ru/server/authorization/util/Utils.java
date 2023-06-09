package ru.server.authorization.util;

import ru.server.authorization.model.Gamer;
import ru.server.authorization.model.ListOfGamers;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import static java.util.Objects.nonNull;

public class Utils {

    public static String generateRandomId() {
        UUID randomId = UUID.randomUUID();
        return randomId.toString();
    }

    public static boolean checkLogin(ListOfGamers gamersByTankBattleId, String gamerLogin) {
        if (nonNull(gamersByTankBattleId)) {
            Stream<String> loginStream = gamersByTankBattleId.getGamers().stream()
                    .map(Gamer::getLogin);
            Optional<String> optionalGamer =
                    Stream.concat(Stream.of(gamersByTankBattleId.getManagerOfGame().getLogin()), loginStream)
                            .filter(gamerLogin::equals)
                            .findFirst();
            if (optionalGamer.isEmpty()) {
                throw new RuntimeException("Этого пользователя нет в списке игроков");
            }
        }
        return true;
    }

}
