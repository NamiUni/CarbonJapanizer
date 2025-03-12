package com.github.namiuni.carbonjapanizer.configuration;

import org.jspecify.annotations.NullMarked;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Comment;

@NullMarked
@ConfigSerializable
@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
public final class JapanizeSettings {

    @Comment("""
            かな漢字変換の標準動作
            プレイヤーが個別にかな漢字変換を設定していない場合はこの値を参照します。
            この値を`IF_JAPANESE`に設定すると、クライアント言語設定が日本語の場合に限りかな漢字変換が試行されるので、
            「標準でENABLEに設定したいが外国語話者の参加も考慮したい」といった場合に便利です。
            有効な値: ENABLE | DISABLE | IF_JAPANESE
            """)
    private PlayerDefault playerDefault = PlayerDefault.IF_JAPANESE;

    @Comment("""
            かな漢字変換の有効/無効を一時的に反転させるメッセージ接頭辞
            この項目の値をメッセージの先頭に付けてから送信すると、かな漢字変換の有効/無効がそのメッセージに限り反転します。
            「外国語話者にメッセージを送る時だけかな漢字変換をオフにしたい」といった場合に便利です。
            値が空っぽの場合は機能しませんが、プレイヤーからこの機能の権限を無くす訳ではありません。
            適用例: $This is a pen.
            有効な値: 文字列 (１文字に限りません)
            """)
    private String switchPrefix = "$"; // TODO quickprefixの動作確認

    @Comment("""
            かな漢字変換されたメッセージの表示形式
            送信メッセージがかな漢字へ変換された際にこの設定のフォーマットが適用されてチャット欄に表示されます。
            CarbonChatのフォーマット設定のタグ<message>の中身をこの項目の値で置換します。
            MiniMessage形式で記述する必要があります。カラーコード(&や§で装飾された文字)には対応していません。
            Minecraftサーバーの設定で署名付きチャットが有効で、なおかつCarbonChatをクロスサーバー運用していない場合、
            この値は`<japanized_message>`で問題ありません。サーバーによって編集されたメッセージはMinecraftのクライアントの標準機能で閲覧できるためです。
            署名付きチャットの詳細についてはこちらをご覧ください。https://gist.github.com/kennytv/ed783dd244ca0321bbd882c347892874
            サンプル: https://webui.advntr.dev/?x=if9RKO7Rn6
            有効な値: MiniMessage形式の文字列
            利用可能なタグ: japanized_message(かな漢字変換後のメッセージ) | original_message(かな漢字変換前のメッセージ)
            """)
    private String messageFormat = "<japanized_message><hover:show_text:'<original_message>'>🔄️</hover>"; // TODO: plain message?

    public PlayerDefault playerDefault() {
        return this.playerDefault;
    }

    public String switchPrefix() {
        return this.switchPrefix;
    }

    public String messageFormat() {
        return this.messageFormat;
    }

    public enum PlayerDefault {
        ENABLE,
        DISABLE,
        IF_JAPANESE
    }
}
