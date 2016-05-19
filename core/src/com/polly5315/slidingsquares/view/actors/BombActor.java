package com.polly5315.slidingsquares.view.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.polly5315.slidingsquares.presentationModel.cells.IBombCell;

public class BombActor extends Actor {
    private final IBombCell _cell;
    private Texture _currentTexture;

    public BombActor(IBombCell cell, int x, int y, Texture idleTexture, final Texture detonatedTexture) {
        if (cell == null)
            throw new IllegalArgumentException("cell cannot be null");
        if (idleTexture == null)
            throw new IllegalArgumentException("normalTexture cannot be null");
        if (detonatedTexture == null)
            throw new IllegalArgumentException("detonatedTexture cannot be null");
        _cell = cell;
        setPosition(x * 8, y * 8);
        setSize(8, 8);
        _currentTexture = cell.isDetonated() ? detonatedTexture : idleTexture;
        _cell.addListener(new IBombCell.IListener() {
            @Override
            public void onDetonated(IBombCell cell) {
                _currentTexture = detonatedTexture;
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(_currentTexture, getX(), getY());
    }
}