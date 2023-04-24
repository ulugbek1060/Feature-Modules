package uz.bigboys.multomodulewithfeatures.glue.orders.di

import com.example.orders.presentation.OrdersRouter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import uz.bigboys.multomodulewithfeatures.glue.orders.AdapterOrdersRouter

@Module
@InstallIn(ActivityRetainedComponent::class)
interface RouterModule {

    @Binds
    fun bindRouter(ordersRouter: AdapterOrdersRouter): OrdersRouter

}