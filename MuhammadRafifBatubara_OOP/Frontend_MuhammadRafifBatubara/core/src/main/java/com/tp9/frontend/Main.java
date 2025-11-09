package com.tp9.frontend;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* * TODO: Implementasikan class Player, interface Command, dan class MoveCommand
 * (Anda bisa membuatnya sebagai inner class di bawah atau file .java terpisah)
 */
/*
* TODO: Implementasikan interface ScoreObserver, class ScoreSystem, dan class
ScoreDisplay
* (Anda bisa membuatnya sebagai inner class di bawah atau file .java terpisah)
*/
public class Main extends ApplicationAdapter {
    private ShapeRenderer shapeRenderer;

    // TODO: Deklarasikan Attribute untuk Player (Receiver)
    private Player player;

    // TODO: Deklarasikan Attribute untuk ScoreSystem (Subject) dan ScoreDisplay
    private ScoreSystem scoreSystem;
    private ScoreDisplay scoreDisplay;
    // TODO: Deklarasikan Attribute Map untuk menyimpan Command input (Invoker)
    private Map<Integer, Command> inputCommands;
    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();

        // TODO: Inisialisasi Player di tengah layar
        player = new Player(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);

        // TODO: Inisialisasi ScoreSystem dan ScoreDisplay
        scoreSystem = new ScoreSystem();
        scoreDisplay = new ScoreDisplay();

        // TODO: Daftarkan scoreDisplay sebagai observer ke scoreSystem
        scoreSystem.registerObserver(scoreDisplay);
        // TODO: Inisialisasi Map inputCommands
        inputCommands = new HashMap<>();

        // TODO: Isi Map inputCommands dengan key dan command yang sesuai
        // (dx, dy) -> (5,0) = Kanan; (-5,0) = Kiri; (0,5) = Atas; (0,-5) = Bawah
        inputCommands.put(Input.Keys.W, new MoveCommand(player, 0, 5));
        inputCommands.put(Input.Keys.A, new MoveCommand(player, -5, 0)); // Perbaikan
        inputCommands.put(Input.Keys.S, new MoveCommand(player, 0, -5)); // Perbaikan
        inputCommands.put(Input.Keys.D, new MoveCommand(player, 5, 0)); // Perbaikan
        System.out.println("Press W,A,S,D to Move. Press SPACE to add score.");
    }
    @Override
    public void render() {
        // --- Handle Input (Invoker Logic) ---

        // TODO: Cek input W. Jika *baru ditekan*, eksekusi command-nya
        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            inputCommands.get(Input.Keys.W).execute();
        }

        // TODO: Cek input A. Jika *baru ditekan*, eksekusi command-nya
        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            inputCommands.get(Input.Keys.A).execute();
        }
        // TODO: Cek input S. Jika *baru ditekan*, eksekusi command-nya
        if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            inputCommands.get(Input.Keys.S).execute();
        }
        // TODO: Cek input D. Jika *baru ditekan*, eksekusi command-nya
        if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            inputCommands.get(Input.Keys.D).execute();
        }

        // TODO: Cek input SPACE. Jika *baru ditekan*, panggil addScore(10)
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            scoreSystem.addScore(10);
        }
        // --- Render Logic ---
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 1, 0, 1); // Warna kuning

        // TODO: Render Player di posisinya (gunakan getPosition())
        shapeRenderer.circle(player.getPosition().x, player.getPosition().y, 20);

        shapeRenderer.end();
    }
    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }

    // --- Anda bisa meletakkan implementasi class-class di sini sebagai inner class ---

    // public class Player { ... }
    // public interface Command { ... }
    // ... dst ...



}
