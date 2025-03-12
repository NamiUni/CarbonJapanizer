/*
 * CarbonJapanizer
 *
 * Copyright (c) 2025. Namiu/Unitarou
 *                     Contributors []
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.github.namiuni.carbonjapanizer.configuration;

import org.jspecify.annotations.NullMarked;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Comment;

@NullMarked
@ConfigSerializable
@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
public final class PrimaryConfig {

    @Comment("""
            ローマ字のメッセージをかな漢字へ変換する設定
            メッセージのかな漢字変換はメッセージの"送信"時に実行されます。
            """)
    private JapanizeSettings japanizeSettings = new JapanizeSettings();

    //    private DatabaseSettings databaseSettings;

    public JapanizeSettings japanizeSettings() {
        return this.japanizeSettings;
    }
}
