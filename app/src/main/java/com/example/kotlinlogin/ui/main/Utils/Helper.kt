import com.example.kotlinlogin.R

class Helper{
    companion object {
        fun <ArrayList> getVersionsList(): ArrayList {
            var androidVersionList = ArrayList<AndroidVersionModel>()
            androidVersionList.add(AndroidVersionModel(R.drawable.ic_tree_pose, "The tree pose", "0", apiLevel = "30"))
            androidVersionList.add(AndroidVersionModel(R.drawable.ic_yoga_boat_pose, "Boat pose", "0", apiLevel = "30"))
            androidVersionList.add(AndroidVersionModel(R.drawable.ic_ashwasanchalanasan, "Ashwasanchalanasan", "0", apiLevel = "30"))
            androidVersionList.add(AndroidVersionModel(R.drawable.ic_natrangasan, "Natrangasan", "0", apiLevel = "30"))
            androidVersionList.add(AndroidVersionModel(R.drawable.ic_yoga_5, "Shishuasana", "0", apiLevel = "30"))

            return androidVersionList as ArrayList
        }
    }
}