/*
MIT License

Copyright (c) 2021 a-simeshin

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package ru.simeshin;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * https://www.cbr.ru/Queries/UniDbQuery/File/90134/931
 * https://www.cbr.ru/ckki/assignment_unique_id/
 */
@UtilityClass
@SuppressWarnings("unused")
public class CBRUUID {

    /**
     * Способ избежать коллизий тика таймеров при использовании данного метода генерации на разных хостах в качестве
     * библиотеки
     */
    public static EthernetAddress ETHERNET_MAC_ADDRESS;

    /**
     * Получить количество UUID
     *
     * @param count количество получаемых UIDов
     * @return коллекия UIDов
     */
    public List<String> getCbrUUID(final int count) {
        if (count <= 0) {
            return Collections.emptyList();
        }
        return IntStream.range(0, count)
                .mapToObj(x -> getCbrUUID())
                .collect(Collectors.toList());
    }

    /**
     * Генерирует UID по требованиям - https://www.cbr.ru/Queries/UniDbQuery/File/90134/931
     * Формат: NNNNNNNN-NNNN-NNNN-NNNN-NNNNNNNNNNNN-K
     *
     * @return UID в виде строки
     */
    public String getCbrUUID() {
        //Генерируем uuid в FILETIME с тиками в 100 наносекунд
        final UUID uuid = ETHERNET_MAC_ADDRESS == null
                ? Generators.timeBasedGenerator().generate()
                : Generators.timeBasedGenerator(ETHERNET_MAC_ADDRESS).generate();

        //Получаем контрольное число в хексе
        final String cleanedUidString = uuid.toString().replace("-", "");
        final List<Integer> hexBased = new ArrayList<>();
        for (final char ch : cleanedUidString.toCharArray()) {
            hexBased.add(Integer.parseInt(String.valueOf(ch), 16));
        }
        int sum = 0;
        int counter = 1;
        for (final int hex : hexBased) {
            sum += hex * counter;
            counter++;
            if (counter > 10) {
                counter = 1;
            }
        }
        final int controlNumber = sum % 16;

        //Переводим из хекса
        final String stringControlNumber = String.format("%x", controlNumber);

        //Собираем и отдаем
        return uuid + "-" + stringControlNumber;
    }

}
