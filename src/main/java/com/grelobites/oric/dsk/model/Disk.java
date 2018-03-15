package com.grelobites.oric.dsk.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Disk {
    private static final Logger LOGGER = LoggerFactory.getLogger(Disk.class);

    private Track[] tracks;
    private final DiskGeometry geometry;

    public Disk(Track[] tracks, DiskGeometry geometry) {
        this.tracks = tracks;
        this.geometry = geometry;
    }

    public Disk(DiskGeometry geometry) {
        this.geometry = geometry;
        tracks = new Track[geometry.getTrackCount() * geometry.getSideCount()];
        for (int i = 0; i < tracks.length; i++) {
            TrackGeometry trackGeometry = geometry.getTrackGeometry(i);
            tracks[i] = new Track(trackGeometry.getSectorCount(),
                    trackGeometry.getSectorSize());
        }
    }

    public byte[] getSector(SectorCoordinates coordinates) {
        return getSector(coordinates.getTrack(), coordinates.getSector());
    }

    public byte[] getSector(int track, int sector) {
        return tracks[track].getSector(sector - 1);
    }

    public DiskGeometry getGeometry() {
        return geometry;
    }

}
