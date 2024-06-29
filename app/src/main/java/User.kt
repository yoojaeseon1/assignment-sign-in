import android.os.Parcel
import android.os.Parcelable
import java.time.LocalDate

data class User(var name: String = "empty user",
                var id: String = "",
                var password:String = "",
                val birthYear: String = "",
                val birthMonth: String = "",
                val birthDay: String = "",
                var mbti: String = "") : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:""
    )


    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeString(id)
        dest.writeString(password)
        dest.writeString(birthYear)
        dest.writeString(birthMonth)
        dest.writeString(birthDay)
        dest.writeString(mbti)

    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }

}
