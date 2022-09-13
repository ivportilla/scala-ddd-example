package tv.codely.mooc.video.application.search

import tv.codely.mooc.video.domain.VideoMother
import tv.codely.mooc.video.infrastructure.repository.VideoRepositoryMock
import tv.codely.shared.infrastructure.unit.UnitTestCase

final class VideosSearcherShould extends UnitTestCase with VideoRepositoryMock {
  private val searcher = new VideosSearcher(repository)

  "search all existing videos" in {
    val existingVideo        = VideoMother.random
    val anotherExistingVideo = VideoMother.random
    val existingVideos       = Seq(existingVideo, anotherExistingVideo)

    repositoryShouldFind(existingVideos)

    searcher.all().futureValue shouldBe existingVideos
  }

  "search the last video" in {
    val anotherExistingVideo = VideoMother.random

    repositoryShouldFindLatest(Some(anotherExistingVideo))

    searcher.latest().futureValue shouldBe Some(anotherExistingVideo)
  }
}
