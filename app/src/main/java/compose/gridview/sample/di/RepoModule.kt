package compose.gridview.sample.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepoModule {
//    @Binds
//    abstract fun bindsUserRepo(userRepo: UserRepo): UserRepoContract
//
//    @Binds
//    abstract fun bindsMovieRepo(movieRepo: MovieRepo): MovieRepoContract
}
