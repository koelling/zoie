package proj.zoie.solr;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.apache.lucene.index.IndexCommit;
import org.apache.lucene.index.SegmentInfos;
import org.apache.lucene.store.Directory;

/*
 * This is copied from Lucene's ReaderCommit which, unfortunately, is private.
 */

public class ZoieSolrIndexCommit extends IndexCommit {

    private String segmentsFileName;
    Collection files;
    Directory dir;
    long generation;
    long version;
    final boolean isOptimized;
    final Map userData;

    ZoieSolrIndexCommit(SegmentInfos infos, Directory dir) throws IOException {
      segmentsFileName = infos.getCurrentSegmentFileName();
      this.dir = dir;
      userData = infos.getUserData();
      files = Collections.unmodifiableCollection(infos.files(dir, true));
      version = infos.getVersion();
      generation = infos.getGeneration();
      isOptimized = infos.size() == 1 && !infos.info(0).hasDeletions();
    }

    public boolean isOptimized() {
      return isOptimized;
    }

	@Override
    public String getSegmentsFileName() {
      return segmentsFileName;
    }

	@Override
    public Collection getFileNames() {
      return files;
    }

	@Override
    public Directory getDirectory() {
      return dir;
    }

    public long getVersion() {
      return version;
    }

    public long getGeneration() {
      return generation;
    }

    public boolean isDeleted() {
      return false;
    }

    public Map getUserData() {
      return userData;
    }

}
