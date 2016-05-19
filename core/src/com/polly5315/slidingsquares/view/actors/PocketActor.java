package com.polly5315.slidingsquares.view.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.polly5315.slidingsquares.presentationModel.cells.IPocketCell;

public class PocketActor extends Actor {
    private final IPocketCell _cell;
    private Texture _currentTexture;

    public PocketActor(final IPocketCell cell, int x, int y, final Texture openPocketTexture, final Texture closedPocketTexture) {
        if (cell == null)
            throw new IllegalArgumentException("cell cannot be null");
        if (openPocketTexture == null)
            throw new IllegalArgumentException("openPocketTexture cannot be null");
        if (closedPocketTexture == null)
            throw new IllegalArgumentException("closedPocketTexture cannot be null");
        _cell = cell;
        setPosition(x * 8, y * 8);
        setSize(8, 8);
        switch (cell.getState()) {
            case Closed:
                _currentTexture = closedPocketTexture;
                break;
            case Hidden:
                _currentTexture = null;
                break;
            case Open:
                _currentTexture = openPocketTexture;
                break;
            default:
                throw new IllegalStateException("Unsupported state");
        }
        _cell.addListener(new IPocketCell.IListener() {
            @Override
            public void onStateChanged(IPocketCell cell) {
                switch (cell.getState()) {
                    case Closed:
                        _currentTexture = closedPocketTexture;
                        break;
                    case Hidden:
                        _currentTexture = null;
                        break;
                    case Open:
                        _currentTexture = openPocketTexture;
                        break;
                    default:
                        throw new IllegalStateException("Unsupported state");
                }
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (_currentTexture != null)
            batch.draw(_currentTexture, getX(), getY());
    }
}